package com.serviciosYa.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView errorPage(HttpServletRequest httpServletRequest) {
        ModelAndView errorPage = new ModelAndView("error");

        Integer errorCode = getErrorCode(httpServletRequest);

        String errorMsg = getErrorMsg(errorCode);

        errorPage.addObject("codigo",errorCode);
        errorPage.addObject("mensaje",errorMsg);

        return errorPage;
    }


    private Integer getErrorCode(HttpServletRequest httpServletRequest){
        return (Integer) httpServletRequest.getAttribute("javax.servelt.error.status_code");
    }

    private String getErrorMsg(Integer errorCode) {

        HashMap<Integer,String> errores = new HashMap<>();

        errores.put(400, "El recurso solicitado no existe");
        errores.put(401, "No tiene autorizacion para acceder");
        errores.put(403, "No tiene permisos para acceder al recurso");
        errores.put(404, "El recurso solicitado no fue encontrado");
        errores.put(500, "Ocurrio un error interno del servidor");


        return errores.get(errorCode);
    }


}
