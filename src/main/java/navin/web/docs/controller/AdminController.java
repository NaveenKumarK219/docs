package navin.web.docs.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import navin.web.docs.model.APIStatus;
import navin.web.docs.model.UserInfo;
import navin.web.docs.service.DocsService;
import navin.web.docs.service.UserInfoService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {

    private static final Log log = LogFactory.getLog(AdminController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DocsService docsService;

    @PostMapping("/change-password")
    public APIStatus doChangePassword(@RequestParam("username") String username,
                                   @RequestParam("currentPassword") String currentPassword,
                                   @RequestParam("newPassword") String newPassword) {

        log.info("Changing Password...");
    	APIStatus apiStatus = APIStatus.initApiStatus("password change");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserInfo userInfo =  userInfoService.getUserInfoByEmail(username);
        if(passwordEncoder.matches(currentPassword, userInfo.getPassword())){
            userInfo.setPassword(passwordEncoder.encode(newPassword));
            userInfoService.updateUser(userInfo);
            apiStatus.setStatus("Password Changed Successfully");
        } else {
            apiStatus.setErrorMessage("Current Password doesn't match");
            apiStatus.setStatus("Completed With Error");
        }

        APIStatus.closeApiStatus(apiStatus);

        return apiStatus;
    }

    @GetMapping("/get-app-users")
    public List<UserInfo> getAppUsers(){
        return userInfoService.getAllUsers();
    }
    
    @GetMapping("/get-user-info/{userEmail}")
    public UserInfo getUserInfoOnEmail(@PathVariable("userEmail") String userEmail) {
    	return userInfoService.getUserInfoByEmail(userEmail);
    }

    @PostMapping("/manage-users/{action}/{id}")
    public APIStatus updateUserOnAction(@PathVariable("action") String action,
                                        @PathVariable("id") Integer id){
        APIStatus status = APIStatus.initApiStatus("Manage Users");

        switch (action){

            case "DELETE":
                userInfoService.deleteUser(id);
                status.setStatus("Completed Deletion Process");
                break;

            case "BLOCK":
                userInfoService.blockUser(id);
                status.setStatus("Completed Blocking User");
                break;

            case "UNBLOCK":
                userInfoService.unBlockUser(id);
                status.setStatus("Completed UnBlocking User");
                break;

            default:
                status.setStatus("Action : "+action+" ! Not Yet Implemented");
                break;

        }
        APIStatus.closeApiStatus(status);
        return status;
    }
}
