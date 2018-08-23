package com.wisesignsoft.OperationManagement.db;

import com.wisesignsoft.OperationManagement.bean.WorkOrder;

import io.realm.Realm;

public class WorkOrderDataManager {
    private static WorkOrderDataManager manager;

    public static WorkOrderDataManager newInstance() {
        if (manager == null)
            manager = new WorkOrderDataManager();
        return manager;
    }

    /**
     * 根据ID 修改order的value
     *
     * @param orderId
     * @param newValue
     */
    public void modifyValue(final String orderId, final String newValue) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WorkOrder workOrder = realm.where(WorkOrder.class).equalTo("ID", orderId).findFirst();
                workOrder.setValue(newValue);

            }
        });
        realm.close();
    }

}
