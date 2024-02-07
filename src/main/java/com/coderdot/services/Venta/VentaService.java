package com.coderdot.services.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderdot.dto.response.PedidoDetalleDTO;
import com.coderdot.entities.CajaApertura;
import com.coderdot.entities.Pedido;
import com.coderdot.entities.PedidoDetalle;
import com.coderdot.entities.TipoDocumento;
import com.coderdot.entities.TipoPago;
import com.coderdot.entities.Venta;
import com.coderdot.entities.VentaDetalle;
import com.coderdot.models.MessageResult;
import com.coderdot.repository.VentaRepository;
import com.coderdot.repository.CajaAperturaRepository;
import com.coderdot.repository.PedidoDetalleRepository;
import com.coderdot.repository.PedidoRepository;
import com.coderdot.repository.TipoDocumentoRepository;
import com.coderdot.repository.TipoPagoRepository;
import com.coderdot.repository.VentaDetalleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository _repository;
    private final VentaDetalleRepository _ventaDetalllerepository;
    private final CajaAperturaRepository _cajaAperturaRepository;
    private final PedidoRepository _pedidoRepository;
    private final PedidoDetalleRepository _pedidoDetalleRepository;
    private final TipoPagoRepository _pagoRepository;
    private final TipoDocumentoRepository _documentoRepository;
    private final MessageResult _messageResult;
    
    public VentaService(VentaRepository repository, PedidoRepository pedidoRepository, CajaAperturaRepository cajaAperturaRepository, 
    MessageResult messageResult, PedidoDetalleRepository pedidoDetalleRepository, VentaDetalleRepository ventaDetalllerepository,
    TipoDocumentoRepository documentoRepository, TipoPagoRepository pagoRepository) {
        this._repository = repository;
        this._cajaAperturaRepository = cajaAperturaRepository;
        this._pedidoRepository = pedidoRepository;
        this._messageResult = messageResult;
        this._pedidoDetalleRepository = pedidoDetalleRepository;
        this._ventaDetalllerepository = ventaDetalllerepository;
        this._pagoRepository = pagoRepository;
        this._documentoRepository = documentoRepository;
    }

    public List<Venta> getAll() {
        return _repository.findAll();
    }

    public Optional<Venta> getById(@NonNull Long id) {
        return _repository.findById(id);
    }

    public boolean create(@NonNull Venta entity) {
        try {
            Long cajaApertura_id = entity.getCajaApertura().getId() != null ? entity.getCajaApertura().getId() : 0;
    
            CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
                .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +cajaApertura_id));

            Long pago_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

            TipoPago pago = _pagoRepository.findById(pago_id)
                .orElseThrow(() -> new EntityNotFoundException("TipoPago no encontrada con id: " +pago_id));

            Long documento_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

            TipoDocumento documento = _documentoRepository.findById(documento_id)
                .orElseThrow(() -> new EntityNotFoundException("TipoDocumento no encontrada con id: " +documento_id));
    
            entity.setCajaApertura(cajaApertura);
            entity.setTipoPago(pago);
            entity.setTipoDocumento(documento);

            _repository.save(entity);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean createWithPedido(@NonNull Venta entity, Long pedido_id) {
        try {
    
            
            Long cajaApertura_id = entity.getCajaApertura().getId() != null ? entity.getCajaApertura().getId() : 0;
    
            CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
                .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +cajaApertura_id));

            Long pago_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

            TipoPago pago = _pagoRepository.findById(pago_id)
                .orElseThrow(() -> new EntityNotFoundException("TipoPago no encontrada con id: " +pago_id));

            Long documento_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

            TipoDocumento documento = _documentoRepository.findById(documento_id)
                .orElseThrow(() -> new EntityNotFoundException("TipoDocumento no encontrada con id: " +documento_id));
    
            @SuppressWarnings("null")
            Pedido pedido = _pedidoRepository.findById(pedido_id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrada con id: " +pedido_id));
    
            entity.setCajaApertura(cajaApertura);
            entity.setTipoPago(pago);
            entity.setTipoDocumento(documento);
    

            entity.setEstado(true);

            List<Object[]> detalles = _pedidoDetalleRepository.findPedidoDetalleWithPrecioByPedidoId(pedido.getId());
            List<PedidoDetalleDTO> dtos = new ArrayList<>();

            Float total = (float) 0.0;
            for (Object[] detalle : detalles) {
                PedidoDetalle pedidoDetalle = (PedidoDetalle) detalle[0];
                Float precio = (Float) detalle[1];
                total += pedidoDetalle.getCantidad() * precio;
        
                PedidoDetalleDTO dto = new PedidoDetalleDTO(pedidoDetalle, precio);
                dtos.add(dto);
            }

            entity.setTotal(total);

            Venta venta = _repository.save(entity);
            pedido.setVenta(venta);

            _pedidoRepository.save(pedido);

            for (PedidoDetalleDTO detalle : dtos) {
                VentaDetalle ventaDetalle = new VentaDetalle();
                ventaDetalle.setComida(detalle.getPedidoDetalle().getComida());
                ventaDetalle.setPrecio_unitario(detalle.getPrecio());
                ventaDetalle.setTotal(detalle.getPrecio() * detalle.getPedidoDetalle().getCantidad());
                ventaDetalle.setUnidad(detalle.getPedidoDetalle().getCantidad());
                ventaDetalle.setVenta(venta);
                _ventaDetalllerepository.save(ventaDetalle);
            }


            Float totalFloat = (total instanceof Float) ? (Float) total : total.floatValue();
            cajaApertura.setTotal(cajaApertura.getTotal().floatValue() + totalFloat);

            _cajaAperturaRepository.save(cajaApertura);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(@NonNull Long id,  Venta entity) {

        Long cajaApertura_id = entity.getCajaApertura().getId() != null ? entity.getCajaApertura().getId() : 0;

        CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
            .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +cajaApertura_id));

        Long pago_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

        TipoPago pago = _pagoRepository.findById(pago_id)
            .orElseThrow(() -> new EntityNotFoundException("TipoPago no encontrada con id: " +pago_id));

        Long documento_id = entity.getTipoPago().getId() != null ? entity.getTipoPago().getId() : 0;

        TipoDocumento documento = _documentoRepository.findById(documento_id)
            .orElseThrow(() -> new EntityNotFoundException("TipoDocumento no encontrada con id: " +documento_id));

        entity.setCajaApertura(cajaApertura);
        entity.setTipoPago(pago);
        entity.setTipoDocumento(documento);

        try {
            _repository.findById(id).map(existingEntity -> {
                existingEntity.setCajaApertura(entity.getCajaApertura());
                existingEntity.setCliente(entity.getCliente());
                existingEntity.setDni(entity.getDni());
                existingEntity.setEstado(entity.getEstado());
                existingEntity.setFecha(entity.getFecha());
                existingEntity.setTotal(entity.getTotal());
                existingEntity.setTipoPago(entity.getTipoPago());
                existingEntity.setTipoDocumento(entity.getTipoDocumento());
                existingEntity.setTipo_venta(entity.getTipo_venta());
                return _repository.save(existingEntity);
            });

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public List<Venta> obtenerVentasPorSucursal(Long sucursalId) {
        return _repository.findByCajaAperturaCajaSucursalId(sucursalId);
    }

    public boolean delete(@NonNull Long id) {
        if (_repository.existsById(id)) {
            _repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    @Transactional
    public boolean setClose(Long ventaId) {
        _repository.actualizarEstado(ventaId);

        Venta venta = _repository.findYesById(ventaId);
        
        Long cajaApertura_id = venta.getCajaApertura().getId() != null ? venta.getCajaApertura().getId() : 0;
    
        CajaApertura cajaApertura = _cajaAperturaRepository.findById(cajaApertura_id)
            .orElseThrow(() -> new EntityNotFoundException("CajaApertura no encontrada con id: " +cajaApertura_id));
        
        Float totalFloat = (venta.getTotal() instanceof Float) ? (Float) venta.getTotal() : venta.getTotal().floatValue();
        cajaApertura.setTotal(cajaApertura.getTotal().floatValue() + totalFloat);

        _cajaAperturaRepository.save(cajaApertura);
        
        return true;
    }

    public MessageResult getResult() {
        return this._messageResult;
    }
}