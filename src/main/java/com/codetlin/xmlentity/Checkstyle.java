package com.codetlin.xmlentity;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "checkstyle")
public class Checkstyle
{
    @ElementList(name = "file", inline = true)
    public List<FileXML> files;

    @Attribute(name = "version")
    public String version;
}
