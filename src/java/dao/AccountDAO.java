/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


/**
 *
 * @author PC
 */
public interface AccountDAO {
     public boolean create( model.Account object);

    // update
    public boolean update( model.Account object);

    // delete
   // public boolean delete( model.Account object);

    // find by id
    public  model.Account ShowDetail(int accountId);
    
    public model.Account CheckLogin( String UsernameString, String Password);

    // load list category
 
}
