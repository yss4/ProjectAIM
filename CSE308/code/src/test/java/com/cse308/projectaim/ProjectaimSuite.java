package com.cse308.projectaim;

import com.cse308.projectaim.beans.BeansSuite;
import com.cse308.projectaim.hibernate.stores.AIMStoreSuite;
import com.cse308.projectaim.services.ServicesSuite;
import com.cse308.projectaim.types.TypesSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AIMStoreSuite.class, 
    BeansSuite.class, 
    ServicesSuite.class,
    TypesSuite.class
    })
public class ProjectaimSuite {
}
