package com.mypenbox.mpb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MpbErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MpbErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        String errorPage = "error";
        String pageTitle = "error";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                pageTitle = "page not found";
                errorPage = "error/404";
                LOGGER.error("Error 404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                pageTitle = "Internal server error";
                errorPage = "error/500";
                LOGGER.error("Error 500");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                pageTitle = "forbidden";
                errorPage = "error/403";
                LOGGER.error("Error 403");
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                pageTitle = "bad request";
                errorPage = "error/400";
                LOGGER.error("Error 400");
            }
        }

        model.addAttribute("pageTitle", pageTitle);

        return errorPage;
    }
}
