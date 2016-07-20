/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.Dispositivos;
import com.mosis.negocios.delegate.DelegateDispositivo;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author Owner
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("servicio_dispositivo")
public class ServicioDispositivo {

    DelegateDispositivo dd = new DelegateDispositivo();

    /**
     * no funciona desde facade
     *
     * @param d
     * @return
     */
    @POST
    public Response addDispositivo(Dispositivos d) {
        Dispositivos dispositivos = new Dispositivos();
        dispositivos.setImei(d.getImei());
        dispositivos.setMarca(d.getMarca());
        dispositivos.setModelo(d.getModelo());
        dispositivos.setStatus(d.getStatus());
        dd.addDispositivos(dispositivos);
        return Response.ok().build();
    }
//    public static void main(String[] args) {
//        ServicioDispositivo sd=new ServicioDispositivo();
//        Dispositivos dispositivos=new Dispositivos();
//        dispositivos.setImei("imei");
//        dispositivos.setMarca("marca");
//        dispositivos.setModelo("modelo");
//        dispositivos.setStatus("1");
//        sd.addDispositivo(dispositivos);
//    }

///////////////////////////////////////////////////////////////////////////////////
    /**
     * http://localhost:8084/MOSISServicios/webresources/servicio_dispositivo/1
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public StreamingOutput getDispositivoId(@PathParam("id") int id) {

        Dispositivos dato = dd.getDispositivoId(id);

        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaDispositivoId(out, dato);
            }
        };
    }

    protected void salidaDispositivoId(OutputStream os, Dispositivos dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();
    }

    /**
     *      *
     * http://localhost:8084/MOSISServicios/webresources/servicio_dispositivo/
     *
     * @return
     */
    @GET
    public StreamingOutput getListDispositivos() {
        List<Dispositivos> datos = dd.getListDispositvos();
        return new StreamingOutput() {

            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaListDispositivos(out, datos);
            }
        };
    }

    protected void salidaListDispositivos(OutputStream os, List<Dispositivos> dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();
    }

    /**
     *      *
     * http://localhost:8084/MOSISServicios/webresources/servicio_dispositivo/id
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public Response deleteDispositivo(@PathParam("id") int id) {
        dd.deleteDispositivo(id);
        return Response.ok().build();
    }

    /**
     * sin funcionar
     *
     * @param id
     * @param d
     * @return
     */
    @PUT
    @Path("/{id}")
    public Response updateDispositivo(@PathParam("id") int id, Dispositivos d) {
        Dispositivos dispositivo = new Dispositivos();
        dispositivo.setIdDispositivo(id);
        dispositivo.setImei(d.getImei());
        dispositivo.setMarca(d.getMarca());
        dispositivo.setModelo(d.getModelo());
        dispositivo.setStatus(d.getStatus());
        dd.updateDispositivos(dispositivo);
        return Response.ok().build();
    }

}
