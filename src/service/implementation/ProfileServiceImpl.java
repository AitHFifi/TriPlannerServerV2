/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

/**
 *
 * @author Hp
 */
import dao.ProfileDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Profile;
import service.ProfileService;
import java.util.List;

public class ProfileServiceImpl extends UnicastRemoteObject implements ProfileService {

    public ProfileServiceImpl() throws RemoteException{
    super();
    }
    
    private ProfileDAO profileDAO = new ProfileDAO();

    @Override
    public Profile findById(Long id) {
        return profileDAO.findById(id);
    }

    @Override
    public List<Profile> findAll() {
        return profileDAO.findAll();
    }

    @Override
    public void save(Profile profile) {
        profileDAO.save(profile);
    }

    @Override
    public void update(Profile profile) {
        profileDAO.update(profile);
    }

    @Override
    public void delete(Profile profile) {
        profileDAO.delete(profile);
    }
}
