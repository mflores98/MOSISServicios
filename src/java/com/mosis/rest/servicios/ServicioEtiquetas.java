/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosis.rest.servicios;

import com.google.gson.Gson;
import com.mosis.entidades.Etiquetas;
import com.mosis.entidades.TipoUsuario;
import com.mosis.entidades.Usuarios;
import com.mosis.excepciones.MyException;
import com.mosis.negocios.delegate.DelegateEtiquetas;
import com.mosis.negocios.delegate.DelegateTipoUsuario;
import com.mosis.negocios.delegate.DelegateUsuarios;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * http://localhost:8084/MOSISServicios/webresources/servicio_etiqueta/1
 *
 * @author mflores98
 */
@Path("servicio_etiqueta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioEtiquetas {

    DelegateEtiquetas de = new DelegateEtiquetas();
    DelegateUsuarios du = new DelegateUsuarios();

    @GET
    @Path("/{id}")
    public StreamingOutput getEtiquetaIdProc(@PathParam("id") long id) {
        DelegateEtiquetas de = new DelegateEtiquetas();
        List<Object[]> dato = de.obtenerEtiquetaIdProc((int) id);
        return new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                salidaEtiquetaIdProc(out, dato);
            }
        };
    }

    protected void salidaEtiquetaIdProc(OutputStream os, List<Object[]> dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();
    }

    ////////////////////////////////////////////////////////////////////
    @DELETE
    @Path("/{id}")
    public Response deleteEtiqueta(@PathParam("id") int id) {
        try {
            de.dellEtiqueta(id);
            return Response.ok(id).build();
        } catch (MyException e) {
            System.out.println(e);
        }
        return Response.ok(id).build();
    }

    //metodo por crear proxy
    ////////////////////////////////////////////////////////////////////
//    @GET
//    @Path("/{id}")
//    public StreamingOutput getEtiquetaId(@PathParam("id") int id) throws MyException {
//        Etiquetas dato = de.getEtiquetaId(id);
//        return new StreamingOutput() {
//            @Override
//            public void write(OutputStream out) throws IOException, WebApplicationException {
//                salidaEtiquetaId(out, dato);
//            }
//        };
//    }
    public void salidaEtiquetaId(OutputStream os, Etiquetas dato) throws IOException {
        PrintStream printStream = new PrintStream(os);
        Gson gson = new Gson();
        String toJson = gson.toJson(dato);
        printStream.println(toJson);
        os.flush();

    }
    ////////////////////////////////////////////////////////////////////

//    @POST
//    @Path("/{usuarioID}")
//    public Response aadEtiqueta(@PathParam("usuarioID") String usuarioID, Etiquetas etiqueta) {
//        Etiquetas eti = new Etiquetas();
//        eti.setValorTag(etiqueta.getValorTag());
//        eti.setNombre(etiqueta.getNombre());
//        eti.setLatitud(etiqueta.getLatitud());//getLat());
//        eti.setLongitud(etiqueta.getLongitud());
//        eti.setFechaCreacion(new Date());
//        eti.setFechaModificacion(new Date());
//        Usuarios usuarioid = du.getUsuarioid(Integer.parseInt(usuarioID));
//        eti.setFkIdUsuarioCreo(usuarioid);//setUsuariosByFkIdUsuarioCreo(usuarioid);
//        eti.setFkIdUsuarioModifico(usuarioid);//setUsuariosByFkIdUsuarioModifico(usuarioid);
//        de.addEtiqueta(eti, usuarioid);
//        return Response.ok(eti).build();
//   }
    ///http://localhost:8084/MOSISServicios/webresources/servicio_etiqueta/etiqueta?usuarioID=1
    //?usuarioID={id de usuario que registrara la etiqueta}
    @POST
    @Path("/etiqueta")
    public Response aadEtiqueta(@QueryParam("usuarioID") String usuarioID, Etiquetas etiqueta) {
        /*
         id_etiqueta
         valor_tag
         nombre
         latitud
         longitud
         fecha_creacion
         fecha_modificacion
         */
        /*
            {  
             "valorTag":"valorTag ExPost2",
             "nombre": "nombretag ExPost2",
             "latitud":"latitudtag ExPost2",
             "longitud":"longitud ExPost2"
         }
         
        */
        try {

            Etiquetas eti = new Etiquetas();
            eti.setValorTag(etiqueta.getValorTag());
            eti.setNombre(etiqueta.getNombre());
            eti.setLatitud(etiqueta.getLatitud());//getLat());
            eti.setLongitud(etiqueta.getLongitud());
            eti.setFechaCreacion(new Date());
            eti.setFechaModificacion(new Date());
            Usuarios usuarioid = du.getUsuarioid(Integer.parseInt(usuarioID));
            eti.setFkIdUsuarioCreo(usuarioid);//setUsuariosByFkIdUsuarioCreo(usuarioid);
            eti.setFkIdUsuarioModifico(usuarioid);//setUsuariosByFkIdUsuarioModifico(usuarioid);
            de.addEtiqueta(eti, usuarioid);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Response.ok().build();

    }

////////////////////////////////////////////////////////////////////
//    public static void main(String[] args) throws MyException {
//        ServicioEtiquetas etiquetas=new ServicioEtiquetas();
//        DelegateEtiquetas de = new DelegateEtiquetas();
//
//        Etiquetas etiquetaId = de.getEtiquetaId(1);
//        Gson g = new Gson();
//        String toJson = g.toJson(etiquetaId.getFkIdUsuarioCreo().getIdUsuario());
//        System.out.println(toJson);
//        StreamingOutput etiquetaId = etiquetas.getEtiquetaId(1);
//        System.out.println(etiquetaId);
//
//        DelegateTipoUsuario dtu=new DelegateTipoUsuario();
//        TipoUsuario tipoUsuarioId = dtu.tipoUsuarioId(1);
//        Gson g=new Gson();
//                String toJson = g.toJson(tipoUsuarioId);
//                System.out.println(toJson);
//    }
}
