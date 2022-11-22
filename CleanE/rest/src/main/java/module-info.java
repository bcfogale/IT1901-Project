module CleanE.rest {
    requires spring.boot.starter;
    requires spring.web;
    requires spring.boot;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires CleanE.core;

    opens rest to spring.beans, spring.context, spring.web;
}
