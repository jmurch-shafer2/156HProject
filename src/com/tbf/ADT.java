package com.tbf;

import unl.cse.trucks.Truck;
import unl.cse.trucks.TruckListNode;

public class ADT {

    private TruckListNode next;
    private Truck item;

    public TruckListNode(Truck item) {
        this.item = item;
        this.next = null;
    }

    public Truck getTruck() {
        return item;
    }

    public TruckListNode getNext() {
        return next;
    }

    public void setNext(TruckListNode next) {
        this.next = next;
    }
}
