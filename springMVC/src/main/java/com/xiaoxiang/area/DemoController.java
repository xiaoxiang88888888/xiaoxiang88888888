package com.xiaoxiang.area;

import com.xiaoxiang.base.AbstractController;
import com.xiaoxiang.model.Area;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
@Controller
@RequestMapping(value = "/area")
public class DemoController extends AbstractController {

    @RequestMapping("/path")
    @ResponseBody
    public String byPath() {
        return "Mapped by path!";
    }

    @RequestMapping(value = "/path/*", method = RequestMethod.GET)
    @ResponseBody
    public String byPathPattern(HttpServletRequest request) {
        return "Mapped by path pattern ('" + request.getRequestURI() + "')";
    }

    @RequestMapping(value = "/method", method = RequestMethod.GET)
    @ResponseBody
    public String byMethod() {
        return "Mapped by path + method";
    }

    @RequestMapping(value = "/parameter", method = RequestMethod.GET, params = "foo")
    @ResponseBody
    public String byParameter() {
        return "Mapped by path + method + presence of query parameter!";
    }

    @RequestMapping(value = "/parameter", method = RequestMethod.GET, params = "!foo")
    @ResponseBody
    public String byParameterNegation() {
        return "Mapped by path + method + not presence of query!";
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET, headers = "FooHeader=foo")
    @ResponseBody
    public String byHeader() {
        return "Mapped by path + method + presence of header!";
    }

    @RequestMapping(value = "/notheader", method = RequestMethod.GET, headers = "!FooHeader")
    @ResponseBody
    public String byHeaderNegation() {
        return "Mapped by path + method + absence of header!";
    }


    @RequestMapping(value = "/consumes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String byConsumes(@RequestBody Area javaBean) {
        return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
    }

    @RequestMapping(value = "/produces", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Area byProduces() {
        return new Area();
    }

    @RequestMapping(value = "html", method = RequestMethod.GET)
    public String prepare(Model model) {
        model.addAttribute("foo", "bar");
        model.addAttribute("fruit", "apple");
        return "html";
    }

    @RequestMapping(value = "/viewName", method = RequestMethod.GET)
    public void usingRequestToViewNameTranslator(Model model) {
        model.addAttribute("foo", "bar");
        model.addAttribute("fruit", "apple");
    }

    @RequestMapping(value = "{foo}/{fruit}", method = RequestMethod.GET)
    public String pathVars(@PathVariable String foo, @PathVariable String fruit) {
        // No need to add @PathVariables "foo" and "fruit" to the model
        // They will be merged in the model before rendering
        return "html";
    }

    @RequestMapping(value = "/response/charset/produce", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String responseProducesConditionCharset() {
        return "こんにちは世界！ (\"Hello world!\" in Japanese)";
    }

    @RequestMapping(value = "/response/entity/status", method = RequestMethod.GET)
    public ResponseEntity<String> responseEntityStatusCode() {
        return new ResponseEntity<String>("The String ResponseBody with custom status code (403 Forbidden)",
                HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/response/entity/headers", method = RequestMethod.GET)
    public ResponseEntity<String> responseEntityCustomHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain",
                headers, HttpStatus.OK);
    }

    //http://www.xiaoxiang.com:8088/area/redirect/uriTemplate
    @RequestMapping(value = "/redirect/uriTemplate", method = RequestMethod.GET)
    public String uriTemplate(RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("account", "肖sdf");  // Used as URI template variable
        redirectAttrs.addAttribute("date", "2012-01-12");  // Appended as a query parameter
        return "redirect:/area/redirect/{account}";
    }

    @RequestMapping(value = "/redirect/uriComponentsBuilder", method = RequestMethod.GET)
    public String uriComponentsBuilder() {
        String date = "2012-02-12";
        UriComponents redirectUri = UriComponentsBuilder.fromPath("/area/redirect/{account}").queryParam("date", date)
                .build().expand("a123").encode();
        return "redirect:" + redirectUri.toUriString();
    }

    @RequestMapping(value = "/redirect/{account}", method = RequestMethod.GET)
    public String show(@PathVariable String account, @RequestParam(required = false) String date,ModelMap modelMap) {
        System.out.println(date);
        System.out.println(date);
        //要放入相应的上下文中
        modelMap.put("date", date);
        return "/redirect/redirectResults.vm";
    }

    @RequestMapping(value = "param")
    @ResponseBody
    public String withParam(@RequestParam String foo) {
        return "Obtained 'foo' query parameter value '" + foo + "'";
    }

    @RequestMapping(value = "group")
    @ResponseBody
    public String withParamGroup(Area bean) {
        return "Obtained parameter group " + bean;
    }

    @RequestMapping(value = "path/{var}")
    @ResponseBody
    public String withPathVariable(@PathVariable String var) {
        return "Obtained 'var' path variable value '" + var + "'";
    }

    @RequestMapping(value = "header")
    @ResponseBody
    public String withHeader(@RequestHeader String Accept) {
        return "Obtained 'Accept' header '" + Accept + "'";
    }

    @RequestMapping(value = "cookie")
    @ResponseBody
    public String withCookie(@CookieValue String openid_provider) {
        return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
    }

    @RequestMapping(value = "body", method = RequestMethod.POST)
    @ResponseBody
    public String withBody(@RequestBody String body) {
        return "Posted request body '" + body + "'";
    }

    @RequestMapping(value = "entity", method = RequestMethod.POST)
    @ResponseBody
    public String withEntity(HttpEntity<String> entity) {
        return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
    }

}
