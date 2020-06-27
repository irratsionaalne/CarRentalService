package CarRentalService.CRS.Controllers;

import CarRentalService.CRS.Models.Branch;
import CarRentalService.CRS.Services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showAllBranches(Model model) {
        List<Branch> branches = branchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "show-all-branches";
    }

    @GetMapping("/add")
    public String addBranchForm(Model model) {
        return "add-branch";
    }

    @PostMapping("/add")
    public String addBranch(Branch branch, Model model) throws Exception {
        boolean createResult = branchService.createBranch(branch);

        if (createResult) {
            model.addAttribute("message", "Branch has been successfully created.");
            model.addAttribute("messageType", "success");
            return showAllBranches(model);
        } else {
            model.addAttribute("branch", branch);
            model.addAttribute("message", "Error in creating a branch.");
            model.addAttribute("messageType", "error");
            return addBranchForm(model);
        }
    }

    @GetMapping("/update")
    public String updateBranchForm(Model model) {
        return "update-branch";
    }

    @PostMapping("/update/{id}")
    public String updateBranch(@PathVariable("id") Long branchId, Branch branch, Model model) {
        branch.setId(branchId);
        boolean updateResult = branchService.updateBranch(branch);

        if (updateResult) {
            model.addAttribute("message", "Branch has been successfully updated.");
            model.addAttribute("messageType", "success");
            return showAllBranches(model);
        } else {
            model.addAttribute("branch", branch);
            model.addAttribute("message", "Error in updating branch");
            model.addAttribute("messageType", "error");
            return updateBranchForm(model);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") Long branchId, Model model) throws Exception {
        boolean deleteResult = branchService.deleteBranch(branchId);

        if (deleteResult) {
            model.addAttribute("message", "Branch has been successfully deleted");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in cancelling branch.");
            model.addAttribute("messageType", "error");
        }

        return showAllBranches(model);
    }

    @GetMapping("/restore/{id}")
    public String restoreBranch(@PathVariable("id") Long branchId, Model model) throws Exception {
        boolean restoreResult = branchService.restoreBranch(branchId);

        if (restoreResult) {
            model.addAttribute("message", "Branch has been successfully restored.");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Error in restoring branch.");
            model.addAttribute("messageType", "error");
        }
        return showAllBranches(model);
    }

}
