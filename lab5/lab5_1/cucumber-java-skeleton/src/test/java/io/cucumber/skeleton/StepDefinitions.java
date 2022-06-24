package io.cucumber.skeleton;

import io.cucumber.java.en.Given;

public class StepDefinitions {

    Belly belly = new Belly();

    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        belly.eat(cukes);
    }

    @Given("I wait {int} hour")
    public void I_wait_hour(int hours) {
        belly.wait(hours);
    }

    @Given("my belly should growl")
    public void my_belly_should_growl() {
        belly.growl();
    }
}
