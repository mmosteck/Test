package com.codetlin.xmlentity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "file")
public class FileXML
{
    @ElementList(name = "error", inline = true)
    public List<Error> errors;
    @Attribute(name = "name")
    public String name;
}
