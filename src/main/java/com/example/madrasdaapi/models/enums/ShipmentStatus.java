package com.example.madrasdaapi.models.enums;

public enum ShipmentStatus {
     Order_Placed(5, "Order Placed"),
     Shipped(6, "Shipped"),
     Delivered(7, "Delivered"),
     Cancelled(8, "Cancelled"),
     RTO_Initiated(9, "RTO Initiated"),
     RTO_Delivered(10, "RTO Delivered"),
     Lost(12, "Lost"),
     Pickup_Error(13, "Pickup Error"),
     RTO_Acknowledged(14, "RTO Acknowledged"),
     Pickup_Rescheduled(15, "Pickup Rescheduled"),
     Cancellation_Requested(16, "Cancellation Requested"),
     Out_For_Delivery(17, "Out For Delivery"),
     In_Transit(18, "In Transit"),
     Out_For_Pickup(19, "Out For Pickup"),
     Pickup_Exception(20, "Pickup Exception"),
     Undelivered(21, "Undelivered"),
     Delayed(22, "Delayed"),
     Partial_Delivered(23, "Partial Delivered"),
     Destroyed(24, "Destroyed"),
     Damaged(25, "Damaged"),
     Fulfilled(26, "Fulfilled"),
     Reached_at_Destination(38, "Reached at Destination"),
     Misrouted(39, "Misrouted"),
     RTO_NDR(40, "RTO NDR"),
     RTO_OFD(41, "RTO OFD"),
     Picked_Up(42, "Picked Up"),
     Self_Fulfilled(43, "Self Fulfilled"),
     DISPOSED_OFF(44, "DISPOSED OFF"),
     CANCELLED_BEFORE_DISPATCHED(45, "CANCELLED BEFORE DISPATCHED"),
     RTO_IN_TRANSIT(46, "RTO IN TRANSIT"),
     QC_Failed(47, "QC Failed"),
     Reached_Warehouse(48, "Reached Warehouse"),
     Custom_Cleared(49, "Custom Cleared"),
     In_Flight(50, "In Flight"),
     Handover_to_Courier(51, "Handover to Courier"),
     Shipment_Booked(52, "Shipment Booked"),
     In_Transit_Overseas(54, "In Transit Overseas"),
     Connection_Aligned(55, "Connection Aligned"),
     Reached_Overseas_Warehouse(56, "Reached Overseas Warehouse"),
     Custom_Cleared_Overseas(57, "Custom Cleared Overseas"),
     Box_Packing(59, "Box Packing");

     private final int code;
     private final String name;

     ShipmentStatus(int code, String name) {
          this.code = code;
          this.name = name;
     }

     public static ShipmentStatus getByCode(int code) {
          ShipmentStatus[] values = ShipmentStatus.values();
          if (code >= 0 && code < values.length) {
               return values[code];
          }
          throw new IllegalArgumentException("Invalid code: " + code);
     }

     public static String getNameByCode(int code) {
          for (ShipmentStatus status : values()) {
               if (status.code == code) {
                    return status.name;
               }
          }
          return null;
     }

     public String getName() {
          return name;
     }
}

