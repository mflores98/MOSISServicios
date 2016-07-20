/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.TipoUsuario;
import com.mosis.entidades.Usuarios;
import com.mosis.negocios.delegate.DelegateTipoUsuario;
import com.mosis.negocios.delegate.DelegateUsuarios;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * http://localhost:8084/MOSISServicios/webresources/servicio_usuarios/
 *
 * @author Owner
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("servicio_usuarios")
public class ServicioUsuarios {

    DelegateUsuarios du = new DelegateUsuarios();
    DelegateTipoUsuario dtu = new DelegateTipoUsuario();

    /**
     *
     * @param tipoUsuarioID
     * @param u
     * @return
     * http://localhost:8084/MOSISServicios/webresources/servicio_usuarios?tipoUsuarioID=1
     * ?tipoUsuarioID= id de tipo usuario
     *
     * {
     * "nombre":"Juan", "ap": "Lopez", "am":"Perez", "status":"1",
     * "password":"mi$@3password", "user":"user232" }
     */
    @POST
    public Response addUsuarios(@QueryParam("tipoUsuarioID") String tipoUsuarioID, Usuarios u) {
        Usuarios usuario = new Usuarios();

        TipoUsuario find = dtu.getTipoUsuarioId(Integer.parseInt(tipoUsuarioID));

        usuario.setNombre(u.getNombre());
        usuario.setAp(u.getAp());
        usuario.setAm(u.getAm());
        usuario.setUser(u.getUser());
        usuario.setPassword(u.getPassword());
        usuario.setStatus(u.getStatus());
        usuario.setFkIdTipoUsuario(find);
        du.addUsuarios(usuario, find);

        return Response.ok().build();
    }

    /**
     * ?tipoUsuarioID=4 { "idUsuario":11, "nombre":"Raul", "ap": "Gomez",
     * "am":"Ramirez", "status":"1", "password":"mi$@3password", "user":"usuario
     * editado" }
     *
     * @param tipoUsuarioID
     * @param u
     * @return
     */
    @PUT
    public Response updateUsuarios(@QueryParam("tipoUsuarioID") String tipoUsuarioID, Usuarios u) {
        Usuarios usuarios = new Usuarios();
        TipoUsuario tipoUsuarioFind = dtu.getTipoUsuarioId(Integer.parseInt(tipoUsuarioID));

        usuarios.setIdUsuario(u.getIdUsuario());
        usuarios.setNombre(u.getNombre());
        usuarios.setAp(u.getAp());
        usuarios.setAm(u.getAm());
        usuarios.setPassword(u.getPassword());
        usuarios.setStatus(u.getStatus());
        usuarios.setUser(u.getUser());
        usuarios.setFkIdTipoUsuario(tipoUsuarioFind);
        du.update(usuarios, tipoUsuarioFind);
        /*
         "id_usuario":11,
         "nombre":"Raul",
         "ap": "Gomez",
         "am":"Ramirez",
         "status":"1",
         "password":"mi$@3password",
         "user":"rgr9k3"
         */

//        System.out.println("getIdUsuario: " + u.getIdUsuario());
//        System.out.println("getNombre: " + u.getNombre());
//        System.out.println("ap: " + u.getAp());
//        System.out.println("am: " + u.getAm());
//        System.out.println("status: " + u.getStatus());
//        System.out.println("pass: " + u.getPassword());
//        System.out.println("user: " + u.getUser());
//        System.out.println("fktipoUsuarioID: " + tipoUsuarioID);
        return Response.ok().build();
    }

////    public static void main(String[] args) {
////
////    }
    /**
     *  *http://localhost:8084/MOSISServicios/webresources/servicio_usuarios/1
     *
     * @param id
     * @return Estado HTTP 500 - java.lang.UnsupportedOperationException:
     * Attempted to serialize java.lang.Class:
     * org.hibernate.proxy.HibernateProxy. Forgot to register a type adapter?
     *
     */
    @GET
    @Path("/{id}")
    public StreamingOutput getUsuarioId(@PathParam("id") int id) {
        Usuarios dato = du.getUsuarioid(id);
        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaUsuarioId(out, dato);
            }
        };
    }

    public void salidaUsuarioId(OutputStream os, Usuarios dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();

    }

    /**
     * http://localhost:8084/MOSISServicios/webresources/servicio_usuarios/
     *
     * @return Estado HTTP 500 - java.lang.UnsupportedOperationException:
     * Attempted to serialize java.lang.Class:
     * org.hibernate.proxy.HibernateProxy. Forgot to register a type adapter?
     */
    @GET
    public StreamingOutput getListUsuarios() {
        List<Usuarios> dato = du.getListUsuarios();
        return new StreamingOutput() {

            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaListUsuarios(out, dato);
            }
        };
    }

    public void salidaListUsuarios(OutputStream os, List<Usuarios> dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") int id) {
        du.deleteUsuario(id);
        return Response.ok().build();
    }
//    public static void main(String[] args) {
//        ServicioUsuarios su=new  ServicioUsuarios();
//        su.deleteUsuario(20);
//             
//    }
}
