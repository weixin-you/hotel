package com.weixin.hotelapp;

import java.util.ArrayList;

public class TenantList {

    private ArrayList<Tenant> tenantList;
    private final int MAX;

    public TenantList(int maxRoomNum){
        tenantList = new ArrayList<>();
        MAX = maxRoomNum;
    }

    public boolean addTenant(Tenant tenant){
      if(!isFull()){
          tenantList.add(tenant);
          return true;
      }
      return false;
    }
    public void removeTenant(int roomNum){
        Tenant searched = searchTenant(roomNum);
        if(searchTenant(roomNum) != null){
            tenantList.remove(searched);
        }
    }
    public Tenant searchTenant(int roomNum){
        for (Tenant tenant: tenantList) {
            if(tenant.getRoomNumber() == roomNum){
                return tenant;
            }
        }
        return null;
    }

    public boolean isFull(){
        return tenantList.size() == MAX;
    }
    public boolean isEmpty(){
        return tenantList.isEmpty();
    }

    public int getSize(){
        return tenantList.size();
    }

    public ArrayList<Tenant> getTenantList() {
        return tenantList;
    }

    @Override
    public String toString(){
        return tenantList.toString();
    }

}
