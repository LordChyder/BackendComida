package com.coderdot.services.Compra;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coderdot.entities.User;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Compra;
import com.coderdot.entities.Proveedor;
import com.coderdot.repository.CompraRepository;
import com.coderdot.repository.ProveedorRepository;
import com.coderdot.repository.CajaAperturaRepository;
import com.coderdot.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompraService implements ICompraService {

    private final CompraRepository _repository;
    private final UserRepository _userRepository;
    private final CajaAperturaRepository _cajaAperturaRepository;
    private final ProveedorRepository _proveedorRepository;
    
    public CompraService(CompraRepository repository, UserRepository userRepository
    , CajaAperturaRepository cajaAperturaRepository, ProveedorRepository proveedorRepository) {
        this._repository = repository;
        this._userRepository = userRepository;
        this._cajaAperturaRepository = cajaAperturaRepository;
        this._proveedorRepository = proveedorRepository;
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
    
            Long inventario_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;
    
            CajaApertura inventario = _cajaAperturaRepository.findById(inventario_id)
                .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +inventario_id));
    
            entity.setUser(user);
            entity.setCajaApertura(inventario);
            entity.setProveedor(proveedor);

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

        Long inventario_id = entity.getUser().getId() != null ? entity.getUser().getId() : 0;

        CajaApertura inventario = _cajaAperturaRepository.findById(inventario_id)
            .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +inventario_id));

        entity.setUser(user);
        entity.setCajaApertura(inventario);
        entity.setProveedor(proveedor);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setEntrada(entity.getEntrada());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setUser(entity.getUser());
                existingEntity.setCajaApertura(entity.getCajaApertura());
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
}