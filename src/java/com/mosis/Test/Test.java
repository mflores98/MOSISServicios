/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mosis.entidades.TipoUsuario;
import com.mosis.entidades.Usuarios;
////import com.mosis.entidades.Etiquetas;
//import com.mosis.entidades.TipoUsuario;
//import com.mosis.entidades.Usuarios;
import com.mosis.excepciones.MyException;
import com.mosis.negocios.delegate.DelegateEtiquetas;
import com.mosis.negocios.integracion.ServiceFacadeLocator;
//import com.mosis.persistencia.BaseDAO;
//import com.mosis.persistencia.integracion.ServiceLocator;
//import java.util.List;

/**
 *
 * @author mflores98
 */
public class Test {

    public static void main(String[] args) throws MyException {
//        BaseDAO baseDAO = new BaseDAO<>();
//        baseDAO.setTipo(TipoUsuario.class);
//        List<TipoUsuario> findAll = baseDAO.findAll();
//        for (TipoUsuario tipoUsuario : findAll) {
//            System.out.println(tipoUsuario.getTipoUsuario());
//        }

        GsonBuilder b = new GsonBuilder();

        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

        Gson gson = b.create();

        TipoUsuario tipoUsuarioID = ServiceFacadeLocator.getFacadeTipoUsuario().tipoUsuarioID(1);

        Usuarios usuariosId = ServiceFacadeLocator.getFacadeUsuarios().usuariosId(1);

//        Integer idTipoUsuario = tipoUsuarioID.getIdTipoUsuario();
//        System.out.println(idTipoUsuario);
//        
//        System.out.println(usuariosId.getFkIdTipoUsuario().getIdTipoUsuario());
//        
        // gson.toJson(usuariosId);
        //String toJson = gson.toJson(tipoUsuarioID);
        // System.out.println(toJson);
//        DelegateEtiquetas de = new DelegateEtiquetas();
//        Etiquetas etiquetaId = de.getEtiquetaId(1);
//
//        String toJson = gson.toJson(etiquetaId);
//        System.out.println(toJson);
//        Test test = new Test();
//        Usuarios usuariosGet = test.usuariosGet(1);
//
//        System.out.println(usuariosGet.getFkIdTipoUsuario());
//
//    }
//    public Usuarios usuariosGet(int id) {
//
//        ServiceLocator.getInstance().setTipo(Usuarios.class);
//        Usuarios find = (Usuarios) ServiceLocator.getInstance().find(id);
//
//        return find;
//    }
        Gson gson1 = new Gson();
        
        

    }
    

}
