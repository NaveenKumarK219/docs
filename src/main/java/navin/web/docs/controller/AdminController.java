package navin.web.docs.controller;

import navin.web.docs.model.APIStatus;
import navin.web.docs.model.UserInfo;
import navin.web.docs.service.UserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Log log = LogFactory.getLog(AdminController.class);
    @Autowired
    private UserInfoService userInfoService;

    private APIStatus initApiStatus(String apiName){
       APIStatus apiStatus = new APIStatus();
       apiStatus.setApiName(apiName);
       apiStatus.setStartTime(new Date());
       apiStatus.setStatus("Started");
       return apiStatus;
    }

    private APIStatus closeApiStatus(APIStatus  apiStatus){
        apiStatus.setEndTime(new Date());
        apiStatus.setExecutionTime(apiStatus.getEndTime().getTime() - apiStatus.getStartTime().getTime());
        return apiStatus;
    }

    @PostMapping("/change-password")
    public APIStatus doChangePassword(@RequestParam("username") String username,
                                   @RequestParam("currentPassword") String currentPassword,
                                   @RequestParam("newPassword") String newPassword) {

        APIStatus apiStatus = initApiStatus("password change");
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

        closeApiStatus(apiStatus);

        return apiStatus;
    }
}
