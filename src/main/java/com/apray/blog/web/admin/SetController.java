package com.apray.blog.web.admin;

import com.apray.blog.po.Set;
import com.apray.blog.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class SetController {

    @Autowired
    private SetService setService;

    @GetMapping("/sets")
    public String sets(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page", setService.listSet(pageable));
        return "admin/sets";
    }

    @GetMapping("/sets/input")
    public String input(Model model){
        model.addAttribute("set", new Set());
        return "admin/set_input";
    }

    @GetMapping("/sets/{id}/input")
    public String editInput(@PathVariable  Long id, Model model){
        model.addAttribute("set", setService.getSet(id));
        return "admin/set_input";
    }




    @PostMapping("/sets")
    public String post(Set set, BindingResult result, RedirectAttributes attributes){
        Set s1 = setService.getSetByName(set.getName());
        if (s1 != null) {
            result.rejectValue("name", "nameError", "文集已存在。");
        }
        if (result.hasErrors()) {
            return "admin/set_input";
        }
        Set s = setService.saveSet(set);
        if (s == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/sets";
    }

    @PostMapping("/sets/{id}")
    public String edit(Set set, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Set s1 = setService.getSetByName(set.getName());
        if (s1 != null) {
            result.rejectValue("name", "nameError", "文集已存在。");
        }
        if (result.hasErrors()) {
            return "admin/set_input";
        }
        Set s = setService.updateSet(id, set);
        if (s == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/sets";
    }

    @GetMapping("/sets/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){

        setService.deleteSet(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/sets";
    }
}
