/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Bill;

/**
 *
 * @author PC
 */
public interface BillService {
    
    public List<Bill> getBillbyAccountId(int accountId);
       public boolean create(Bill object);
    
}
