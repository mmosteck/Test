package com.codetlin.xmlentity;

import org.simpleframework.xml.Attribute;

public class Error
{
    @Attribute(name = "source")
    public String source;

    @Attribute(name = "message")
    public String message;

    @Attribute(name = "severity")
    public String severity;

    @Attribute(name = "column")
    public int column;

    @Attribute(name = "line")
    public int line;
}
