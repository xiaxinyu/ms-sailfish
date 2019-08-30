package com.sailfish.websocket.controller;

import com.sailfish.domain.ResponseEntity;
import com.sailfish.utils.JSONUtils;
import com.sailfish.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Controller
 *
 * @author XIAXINYU3
 * @date 2019.8.30
 */
@Controller
@RequestMapping("/checkcenter")
@Slf4j
public class CheckCenterController {

    @RequestMapping(value = "/socket/{cid}", method = RequestMethod.GET)
    public ModelAndView socket(@PathVariable String cid, HttpServletRequest request) {
        log.info("Enter index web mapping");
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        view.addObject("basePath", request.getContextPath());
        view.addObject("cid", cid);
        return view;
    }

    @RequestMapping("/socket/push/{cid}")
    @ResponseBody
    public ResponseEntity<String> pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            return JSONUtils.getFail(cid + "#" + e.getMessage());
        }
        return JSONUtils.getSuccess(cid);
    }
}
