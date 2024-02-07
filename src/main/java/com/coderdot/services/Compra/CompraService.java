package com.coderdot.services.Compra;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderdot.entities.User;
import com.coderdot.models.MessageResult;
import com.coderdot.dto.request.TrabajadorCompraRequest;
import com.coderdot.entities.Compra;
import com.coderdot.entities.Inventario;
import com.coderdot.entities.Proveedor;
import com.coderdot.repository.CompraRepository;
import com.coderdot.repository.InventarioRepository;
import com.coderdot.repository.ProveedorRepository;
import com.coderdot.repository.CajaAperturaRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@SuppressWarnings("null")
@Service
public class CompraService implements ICompraService {

    private final CompraRepository _repository;
    private final UserRepository _userRepository;
    private final ProveedorRepository _proveedorRepository;
    private final InventarioRepository _inventarioRepository;
    private final MessageResult _messageResult;
    
    public CompraService(CompraRepository repository, UserRepository userRepository, 
    CajaAperturaRepository cajaAperturaRepository, ProveedorRepository proveedorRepository, InventarioRepository inventarioRepository,  
    MessageResult messageResult) {
        this._repository = repository;
        this._userRepository = userRepository;
        this._messageResult = messageResult;
        this._proveedorRepository = proveedorRepository;
        this._inventarioRepository = inventarioRepository;
    }

    public List<Compra> getAll() {
        return _repository.findAll();
    }

    public Optional<Compra> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Compra entity) {

        try {
            Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            User user = _userRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));
    
            Long proveedor_id = entity.getProveedor().getId() != null ? entity.getProveedor().getId() : 0;
    
            Proveedor proveedor = _proveedorRepository.findById(proveedor_id)
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +proveedor_id));

            Long inventario_id = entity.getProveedor().getId() != null ? entity.getProveedor().getId() : 0;

            Inventario inventario = _inventarioRepository.findById(inventario_id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));
    
            entity.setUser(user);
            entity.setProveedor(proveedor);
            entity.setInventario(inventario);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Compra entity) {
        Long user_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        User user = _userRepository.findById(user_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +user_id));

        Long proveedor_id = entity.getProveedor().getId() != null ? entity.getProveedor().getId() : 0;

        Proveedor proveedor = _proveedorRepository.findById(proveedor_id)
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " +proveedor_id));

        Long inventario_id = entity.getProveedor().getId() != null ? entity.getProveedor().getId() : 0;

        Inventario inventario = _inventarioRepository.findById(inventario_id)
            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " +inventario_id));

        entity.setUser(user);
        entity.setProveedor(proveedor);
        entity.setInventario(inventario);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setEntrada(entity.getEntrada());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setUser(entity.getUser());
                existingEntity.setProveedor(entity.getProveedor());
                return _repository.save(existingEntity);
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(@NonNull Long id) {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean createByTrabajador(@NonNull TrabajadorCompraRequest entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {

            User user =  _userRepository.findByUsername(authentication.getName())
            .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " + authentication.getName()));;

            Proveedor proveedor = _proveedorRepository.findById(entity.getProveedor_id())
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " + entity.getProveedor_id() ));

            Inventario inventario = _inventarioRepository.findById(entity.getInventario_id())
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " + entity.getInventario_id()));
    
            Compra compra = new Compra();
            compra.setEntrada(entity.getEntrada());
            compra.setEstado(entity.getEstado());
            compra.setFecha(new Date());
            compra.setInventario(inventario);
            compra.setProveedor(proveedor);
            compra.setTotal(entity.getTotal());
            compra.setUser(user);

            _repository.save(compra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateByTrabajador(@NonNull Long id, @NonNull TrabajadorCompraRequest entity) {

        try {
            Proveedor proveedor = _proveedorRepository.findById(entity.getProveedor_id())
                .orElseThrow(() -> new EntityNotFoundException("User no encontrada con id: " + entity.getProveedor_id() ));

            Inventario inventario = _inventarioRepository.findById(entity.getInventario_id())
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrada con id: " + entity.getInventario_id()));
    
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setEntrada(entity.getEntrada());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setProveedor(proveedor);
                existingEntity.setInventario(inventario);
                return _repository.save(existingEntity);
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Compra> obtenerComprasPorSucursal(Long sucursalId) {
        return _repository.findByInventarioSucursalId(sucursalId);
    }

    @Transactional
    public boolean setClose(Long compraId) {
        _repository.actualizarEstado(compraId);
        
        return true;
    }
    
    public List<Compra> obtenerComprasEntradaFalseYEstadoTrue() {
        return _repository.findByEntradaFalseAndEstadoTrue();
    }

    public MessageResult getResult() {
        return this._messageResult;
    }
}