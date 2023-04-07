package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.ShipmentDTO;
import com.example.madrasdaapi.dto.ShipmentTrackActivityDTO;
import com.example.madrasdaapi.dto.TrackingDataDTO.ScansItem;
import com.example.madrasdaapi.dto.TrackingDataDTO.TrackingData;
import com.example.madrasdaapi.models.Shipment;
import com.example.madrasdaapi.models.ShipmentTrackActivity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShipmentMapper {
    private final ModelMapper mapper;
    public Shipment mapToShipment(TrackingData trackingData) {
        Shipment shipment = new Shipment();
        shipment.setCourierName(trackingData.getCourierName());
        shipment.setEtd(trackingData.getEtd());
        shipment.setCurrentTimestamp(trackingData.getCurrentTimestamp());
        shipment.setChannel(trackingData.getChannel());
        shipment.setCurrentStatus(trackingData.getCurrentStatus());
        shipment.setCurrentStatusId(trackingData.getCurrentStatusId());
        shipment.setShipmentStatusId(trackingData.getShipmentStatusId());
        shipment.setChannelOrderId(trackingData.getChannelOrderId());
        shipment.setAwb(trackingData.getAwb());
        shipment.setOrderId(trackingData.getOrderId());
        shipment.setShipmentStatus(trackingData.getShipmentStatus());
        shipment.setScans(trackingData.getScans().stream().map(this::mapToShipmentTrackActivity).collect(Collectors.toList()));
        return shipment;
    }

    public ShipmentTrackActivity mapToShipmentTrackActivity(ScansItem scansItem) {
        ShipmentTrackActivity shipmentScan = new ShipmentTrackActivity();
        shipmentScan.setDate(scansItem.getDate());
        shipmentScan.setActivity(scansItem.getActivity());
        shipmentScan.setLocation(scansItem.getLocation());
        return shipmentScan;
    }

    public ShipmentDTO mapToDTO(Shipment shipment) {
        return mapper.map(shipment, ShipmentDTO.class);
    }
}
