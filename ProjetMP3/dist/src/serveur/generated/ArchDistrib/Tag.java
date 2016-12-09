// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
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

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
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

    public static final long serialVersionUID = 1254255994951729010L;
}
