// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `InterfaceMP3.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package ArchDistrib;

public class Tag implements java.lang.Cloneable, java.io.Serializable
{
    public String name;

    public Tag()
    {
        name = "";
    }

    public Tag(String name)
    {
        this.name = name;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Tag _r = null;
        if(rhs instanceof Tag)
        {
            _r = (Tag)rhs;
        }

        if(_r != null)
        {
            if(name != _r.name)
            {
                if(name == null || _r.name == null || !name.equals(_r.name))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::ArchDistrib::Tag");
        __h = IceInternal.HashUtil.hashAdd(__h, name);
        return __h;
    }

    public Tag
    clone()
    {
        Tag c = null;
        try
        {
            c = (Tag)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(name);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        name = __is.readString();
    }

    static public void
    __write(IceInternal.BasicStream __os, Tag __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public Tag
    __read(IceInternal.BasicStream __is, Tag __v)
    {
        if(__v == null)
        {
             __v = new Tag();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final Tag __nullMarshalValue = new Tag();

    public static final long serialVersionUID = -1669300366L;
}
