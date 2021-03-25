package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;
//////////////////////////////////////////////////////
//2. add an index method that responds to /employers with a list of all employers in the database//
///////////////////////////////////////////////////////////
    @GetMapping("")
    public String index(Model model) { //add attribute means send to view, values on a bus going to model
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }
//////////////////////////////////////////////////////
    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        } else {
            employerRepository.save(newEmployer);
        }

        return "redirect:";
    }


    //////////////////////////////////////////////////////////////////////
    //displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object. It will make use of that employer object’s id field to grab the correct information from employerRepository. optEmployer is currently initialized to null. Replace this using the .findById() method with the right argument to look for the given employer object from the data layer.
/////////////////////////////////////////////////////////////////////
    //Does this work correctly? Not sure yet
    /////////////////////////////////////////////////////////////

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable Integer employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
