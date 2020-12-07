package com.apray.blog.web.admin;

import com.apray.blog.po.Tag;
import com.apray.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tag_input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable  Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag_input";
    }




    @PostMapping("/tags")
    public String post(Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag t1 = tagService.getTagByName(tag.getName());
        if (t1 != null) {
            result.rejectValue("name", "nameError", "文集已存在。");
        }
        if (result.hasErrors()) {
            return "admin/tag_input";
        }
        Tag s = tagService.saveTag(tag);
        if (s == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String edit(Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
        Tag s1 = tagService.getTagByName(tag.getName());
        if (s1 != null) {
            result.rejectValue("name", "nameError", "文集已存在。");
        }
        if (result.hasErrors()) {
            return "admin/tag_input";
        }
        Tag s = tagService.updateTag(id, tag);
        if (s == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){

        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
