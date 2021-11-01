package com.nguyenduc.service.login.role;

import com.nguyenduc.model.login.Role;
import com.nguyenduc.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
