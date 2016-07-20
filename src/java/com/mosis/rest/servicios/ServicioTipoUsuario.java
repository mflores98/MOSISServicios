/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.TipoUsuario;
import com.mosis.negocios.delegate.DelegateTipoUsuario;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author Owner
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("servicio_tipo_usuario")
public class ServicioTipoUsuario {

    DelegateTipoUsuario dtu = new DelegateTipoUsuario();

    /**
     * http://localhost:8084/MOSISServicios/webresources/servicio_tipo_usuario/
     * funciona
     *
     * @return
     */
    @GET
    public StreamingOutput getListTiposUsuario() {
        List<TipoUsuario> datos = dtu.getListTipoUsuario();
        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                //salidaTiposUsuario(this, datos);
                salidaTiposUsuario(out, datos);
            }
        };
    }

    protected void salidaTiposUsuario(OutputStream os, List<TipoUsuario> datos) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(datos);
        printStream.println(toJson);
        os.flush();
    }

    /**
     *
     * http://localhost:8084/MOSISServicios/webresources/servicio_tipo_usuario/1
     * funciona
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public StreamingOutput getTipoUsuarioId(@PathParam("id") int id) {
        TipoUsuario dato = dtu.getTipoUsuarioId(id);

        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaTipoUsuarioId(out, dato);
            }
        };
    }

    protected void salidaTipoUsuarioId(OutputStream os, TipoUsuario dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();
    }

    /**
     * http://localhost:8084/MOSISServicios/webresources/servicio_tipo_usuario/id
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    public void deleteTipoUsuario(@PathParam("id") int id) {
        dtu.eliminarTipoUsuario(id);

    }
    
//      public static void main(String[] args) {
//        DelegateTipoUsuario ftu = new DelegateTipoUsuario();
//        TipoUsuario dato = ftu.getTipoUsuarioId(1);
//        Gson gson = new Gson();
//        String toJson = gson.toJson(dato);
//        System.out.println(toJson);
//
//    }
//    public static void main(String[] args) {
//        ServicioTipoUsuario stu = new ServicioTipoUsuario();
//        StreamingOutput tipoUsuarioId = stu.getTipoUsuarioId(1);
//        Gson g = new Gson();
//        String toJson = g.toJson(tipoUsuarioId);
//        System.out.println(toJson);
//    }

}
