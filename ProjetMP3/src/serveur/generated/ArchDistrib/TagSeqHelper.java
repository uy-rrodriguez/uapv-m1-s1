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

public final class TagSeqHelper
{
    public static void
    write(IceInternal.BasicStream __os, Tag[] __v)
    {
        if(__v == null)
        {
            __os.writeSize(0);
        }
        else
        {
            __os.writeSize(__v.length);
            for(int __i0 = 0; __i0 < __v.length; __i0++)
            {
                __os.writeObject(__v[__i0]);
            }
        }
    }

    public static Tag[]
    read(IceInternal.BasicStream __is)
    {
        Tag[] __v;
        final int __len0 = __is.readAndCheckSeqSize(1);
        final String __type0 = Tag.ice_staticId();
        __v = new Tag[__len0];
        for(int __i0 = 0; __i0 < __len0; __i0++)
        {
            __is.readObject(new IceInternal.SequencePatcher(__v, Tag.class, __type0, __i0));
        }
        return __v;
    }
}
