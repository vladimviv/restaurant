package io.khasang.restaurant.controller;

import io.khasang.restaurant.entity.Document;
import io.khasang.restaurant.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Document addDocument(@RequestBody Document document){
        return documentService.addDocument(document);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Document> getDocumentList(){
        return documentService.getDocumentList();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Document deleteDocument(@PathVariable(value = "id") String id){
        return documentService.deleteDocument(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Document updateDocument(@RequestBody Document document){
        documentService.updateDocument(document);
        return document;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Document getDocumentById(@PathVariable(value = "id") String id){
        return documentService.getDocumentById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Document> getDocumentListByName(@PathVariable(value = "name") String name){
        return documentService.getDocumentListByName(name);
    }

}
