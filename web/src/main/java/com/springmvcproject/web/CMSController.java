package com.springmvcproject.web;

import com.springmvcproject.core.account.model.User;
import com.springmvcproject.core.account.service.UserServiceImpl;
import com.springmvcproject.core.model.Announcement;
import com.springmvcproject.core.model.Content;
import com.springmvcproject.core.model.File;
import com.springmvcproject.core.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.springmvcproject.core.service.HibernateService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by yektan on 14.12.2016.
 */
@Controller
public class CMSController {

    private HibernateService<Menu, Integer> menuService;
    private HibernateService<Content, Integer> contentService;
    private HibernateService<Announcement,Integer> announcementService;
    private HibernateService<File,Integer> fileService;
    private UserServiceImpl userService;
    private int menuid;


    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "fileService")
    public void setFileService(HibernateService<File, Integer> fs) {
        this.fileService = fs;
    }

    @Autowired
    @Qualifier(value="menuService")
    public void setMenuService(HibernateService<Menu,Integer> ms){
        this.menuService = ms;
    }

    @Autowired
    @Qualifier(value = "contentService")
    public void setContentService(HibernateService<Content,Integer> cs){this.contentService=cs;}

    @Autowired
    @Qualifier(value = "announcementService")
    public void setAnnouncementService(HibernateService<Announcement,Integer> as){this.announcementService=as;}


    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String listMenus(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        List<Menu> mList = this.menuService.list();
        for (Iterator<Menu> iterator = mList.iterator(); iterator.hasNext();) {
            Menu m = iterator.next();
            User menuUser = userService.findByUsername(m.getUser().getUsername());
            if(!menuUser.getId().equals(currentUser.getId())){
                iterator.remove();
            }
        }

        model.addAttribute("menu", new Menu());
        model.addAttribute("listMenus", mList);

        return "menu";
    }

    @RequestMapping(value= "/menu/add", method = RequestMethod.POST)
    public String addMenu(@ModelAttribute("menu") Menu m){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        m.setUser(user);
        this.menuService.add(m);
        return "redirect:/menus";

    }

    @RequestMapping("/menu/remove/{id}")
    public String removeMenu(@PathVariable("id") int id){

        this.menuService.remove(id);
        return "redirect:/menus";
    }

    @RequestMapping("/menu/edit/{id}")
    public String editMenu(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());

        List<Menu> mList = this.menuService.list();
        for (Iterator<Menu> iterator = mList.iterator(); iterator.hasNext();) {
            Menu m = iterator.next();
            User menuUser = userService.findByUsername(m.getUser().getUsername());
            if(!menuUser.getId().equals(currentUser.getId())){
                iterator.remove();
            }
        }

        model.addAttribute("menu", this.menuService.getById(id));
        model.addAttribute("listMenus", mList);

        return "menu";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/contents/{id}", method = RequestMethod.GET)
    public String listContents(Model model, @PathVariable("id") int id) {
        this.menuid=id;

        List<Content> conts = this.contentService.list();
        for (Iterator<Content> iterator = conts.iterator(); iterator.hasNext();) {
            Content c = iterator.next();
            Menu m = menuService.getById(menuid);
            if(c.getMenu().getId()!=m.getId()){
                iterator.remove();
            }
        }


        model.addAttribute("content", new Content());
        model.addAttribute("listContents", conts);

        return "content";
    }

    @RequestMapping(value = "/contents", method = RequestMethod.GET)
    public String listContents(Model model) {
        model.addAttribute("content", new Content());
        model.addAttribute("listContents", this.contentService.list());

        return "content";
    }

    @RequestMapping(value= "/content/add", method ={ RequestMethod.POST })
    public String addContent(@ModelAttribute("content") Content c,@ModelAttribute("file") File f,
                             @RequestParam("fileUpload") CommonsMultipartFile[] fileUpload){
        Menu menu = menuService.getById(menuid);
        c.setMenu(menu);
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                f.setDescription(aFile.getOriginalFilename());
                f.setFile(aFile.getBytes());
                f.setContent(c);
                this.fileService.add(f);
            }
        }

        return "redirect:/contents/"+menuid;
    }

    @RequestMapping("/content/remove/{id}")
    public String removeContent(@PathVariable("id") int id){

        this.contentService.remove(id);
        return "redirect:/contents";
    }

    @RequestMapping("/content/edit/{id}")
    public String editContent(@PathVariable("id") int id, Model model){
        this.menuid=id;

        List<Content> conts = this.contentService.list();
        for (Iterator<Content> iterator = conts.iterator(); iterator.hasNext();) {
            Content c = iterator.next();
            Menu m = menuService.getById(menuid);
            if(c.getMenu().getId()!=m.getId()){
                iterator.remove();
            }
        }
        model.addAttribute("content", this.contentService.getById(id));
        model.addAttribute("listContents", conts);

        return "content";
    }

    //////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/announcements", method = RequestMethod.GET)
    public String listAnnouncements(Model model) throws ParseException {
        Date date = new Date();
        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);


        List<Announcement> aList = this.announcementService.list();
        for (Iterator<Announcement> iterator = aList.iterator(); iterator.hasNext();) {
            Announcement a = iterator.next();
            Date enddate = a.getEnddate();
            String end = new SimpleDateFormat("yyyy-MM-dd").format(enddate);
            if(now.compareTo(end)>0){
                iterator.remove();
            }
        }

        model.addAttribute("announcement", new Announcement());
        model.addAttribute("listAnnouncements", aList);

        return "announcement";
    }

    @RequestMapping(value= "/announcement/add", method = RequestMethod.POST)
    public String addAnnouncement(@ModelAttribute("announcement") Announcement a){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        a.setOwner(user);
        Date date = new Date();
        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
        a.setCreated(java.sql.Date.valueOf(now));
        this.announcementService.add(a);
        return "redirect:/announcements";

    }

    @RequestMapping("/announcement/remove/{id}")
    public String removeAnnouncement(@PathVariable("id") int id){

        this.announcementService.remove(id);
        return "redirect:/announcements";
    }

//    @RequestMapping("/announcement/edit/{id}")
//    public String editAnnouncement(@PathVariable("id") int id, Model model){
//        List<Menu> mList = this.menuService.list();
//        for (Iterator<Menu> iterator = mList.iterator(); iterator.hasNext();) {
//            Menu m = iterator.next();
//            if(!){
//                iterator.remove();
//            }
//        }
//
//        model.addAttribute("menu", this.menuService.getById(id));
//        model.addAttribute("listMenus", mList);
//
//        return "announcement";
//    }
}
