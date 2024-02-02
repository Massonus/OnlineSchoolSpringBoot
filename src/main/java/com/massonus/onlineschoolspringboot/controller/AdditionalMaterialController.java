package com.massonus.onlineschoolspringboot.controller;

import com.massonus.onlineschoolspringboot.entity.AdditionalMaterial;
import com.massonus.onlineschoolspringboot.entity.ResourceType;
import com.massonus.onlineschoolspringboot.service.AdditionalMaterialService;
import com.massonus.onlineschoolspringboot.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class AdditionalMaterialController {

    /*с котроллером работает только Service, не Repo*/
    private final AdditionalMaterialService materialService;
    private final Menu menu;

    @Autowired
    public AdditionalMaterialController(AdditionalMaterialService materialService, Menu menu) {
        this.materialService = materialService;
        this.menu = menu;
    }

    @GetMapping("/material/{id}")
    public String getMaterial(Model model, @PathVariable long id) {
        final AdditionalMaterial material = materialService.getMaterialById(id).orElse(null);
        model.addAttribute("material", material);
        model.addAttribute("menu", menu.getMenuItems());
        return "info/material_info";
    }

    @GetMapping("/all-materials")
    public String home(Model model) {
        model.addAttribute("menu", menu.getMenuItems());
        model.addAttribute("materials", materialService.getMaterialList());
        return "menu/material_menu";
    }

    @PostMapping("/addMaterial")
    public String addMaterial(@RequestParam String task,
                              @RequestParam String type,
                              @RequestParam Long lectureId) {
        ResourceType resourceType = ResourceType.valueOf(type);
        final AdditionalMaterial newMaterial = materialService.createElementByUserForm(task, resourceType, lectureId);
        materialService.saveMaterial(newMaterial);
        return "redirect:/all-materials";
    }

    @GetMapping("/material/delete/{id}")
    public String deleteHomework(@PathVariable Long id) {
        final AdditionalMaterial material = materialService.getMaterialById(id).orElse(null);
        materialService.deleteMaterial(Objects.requireNonNull(material).getId());
        return "redirect:/all-materials";
    }

    @PostMapping("/material/edit/{id}")
    public String postEditPerson(@PathVariable Long id,
                                 @RequestParam String task,
                                 @RequestParam String type,
                                 @RequestParam Long lectureId) {

        final AdditionalMaterial material = materialService.getMaterialById(id).get();
        final AdditionalMaterial editedMaterial = materialService.refactorElementByUserForm(material, task, ResourceType.valueOf(type), lectureId);
        materialService.saveMaterial(editedMaterial);

        return "redirect:/all-materials";
    }

}
