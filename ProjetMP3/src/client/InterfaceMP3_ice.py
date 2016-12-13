# **********************************************************************
#
# Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
#
# This copy of Ice is licensed to you under the terms described in the
# ICE_LICENSE file included in this distribution.
#
# **********************************************************************
#
# Ice version 3.5.1
#
# <auto-generated>
#
# Generated from file `InterfaceMP3.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

import Ice, IcePy

# Start of module ArchDistrib
_M_ArchDistrib = Ice.openModule('ArchDistrib')
__name__ = 'ArchDistrib'

if 'Song' not in _M_ArchDistrib.__dict__:
    _M_ArchDistrib.Song = Ice.createTempClass()
    class Song(object):
        def __init__(self, id=0, name='', artist='', path=''):
            self.id = id
            self.name = name
            self.artist = artist
            self.path = path

        def __hash__(self):
            _h = 0
            _h = 5 * _h + Ice.getHash(self.id)
            _h = 5 * _h + Ice.getHash(self.name)
            _h = 5 * _h + Ice.getHash(self.artist)
            _h = 5 * _h + Ice.getHash(self.path)
            return _h % 0x7fffffff

        def __compare(self, other):
            if other is None:
                return 1
            elif not isinstance(other, _M_ArchDistrib.Song):
                return NotImplemented
            else:
                if self.id is None or other.id is None:
                    if self.id != other.id:
                        return (-1 if self.id is None else 1)
                else:
                    if self.id < other.id:
                        return -1
                    elif self.id > other.id:
                        return 1
                if self.name is None or other.name is None:
                    if self.name != other.name:
                        return (-1 if self.name is None else 1)
                else:
                    if self.name < other.name:
                        return -1
                    elif self.name > other.name:
                        return 1
                if self.artist is None or other.artist is None:
                    if self.artist != other.artist:
                        return (-1 if self.artist is None else 1)
                else:
                    if self.artist < other.artist:
                        return -1
                    elif self.artist > other.artist:
                        return 1
                if self.path is None or other.path is None:
                    if self.path != other.path:
                        return (-1 if self.path is None else 1)
                else:
                    if self.path < other.path:
                        return -1
                    elif self.path > other.path:
                        return 1
                return 0

        def __lt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r < 0

        def __le__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r <= 0

        def __gt__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r > 0

        def __ge__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r >= 0

        def __eq__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r == 0

        def __ne__(self, other):
            r = self.__compare(other)
            if r is NotImplemented:
                return r
            else:
                return r != 0

        def __str__(self):
            return IcePy.stringify(self, _M_ArchDistrib._t_Song)

        __repr__ = __str__

    _M_ArchDistrib._t_Song = IcePy.defineStruct('::ArchDistrib::Song', Song, (), (
        ('id', (), IcePy._t_int),
        ('name', (), IcePy._t_string),
        ('artist', (), IcePy._t_string),
        ('path', (), IcePy._t_string)
    ))

    _M_ArchDistrib.Song = Song
    del Song

if '_t_SongSeq' not in _M_ArchDistrib.__dict__:
    _M_ArchDistrib._t_SongSeq = IcePy.defineSequence('::ArchDistrib::SongSeq', (), _M_ArchDistrib._t_Song)

if '_t_DataSeq' not in _M_ArchDistrib.__dict__:
    _M_ArchDistrib._t_DataSeq = IcePy.defineSequence('::ArchDistrib::DataSeq', (), IcePy._t_byte)

if 'InterfaceMP3' not in _M_ArchDistrib.__dict__:
    _M_ArchDistrib.InterfaceMP3 = Ice.createTempClass()
    class InterfaceMP3(Ice.Object):
        def __init__(self):
            if Ice.getType(self) == _M_ArchDistrib.InterfaceMP3:
                raise RuntimeError('ArchDistrib.InterfaceMP3 is an abstract class')

        def ice_ids(self, current=None):
            return ('::ArchDistrib::InterfaceMP3', '::Ice::Object')

        def ice_id(self, current=None):
            return '::ArchDistrib::InterfaceMP3'

        def ice_staticId():
            return '::ArchDistrib::InterfaceMP3'
        ice_staticId = staticmethod(ice_staticId)

        def addSong(self, name, artist, file, current=None):
            pass

        def removeSong(self, id, current=None):
            pass

        def listSongs(self, current=None):
            pass

        def searchSongs(self, nameRegex, artistRegex, current=None):
            pass

        def playSong(self, id, current=None):
            pass

        def stopSong(self, current=None):
            pass

        def __str__(self):
            return IcePy.stringify(self, _M_ArchDistrib._t_InterfaceMP3)

        __repr__ = __str__

    _M_ArchDistrib.InterfaceMP3Prx = Ice.createTempClass()
    class InterfaceMP3Prx(Ice.ObjectPrx):

        def addSong(self, name, artist, file, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_addSong.invoke(self, ((name, artist, file), _ctx))

        def begin_addSong(self, name, artist, file, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_addSong.begin(self, ((name, artist, file), _response, _ex, _sent, _ctx))

        def end_addSong(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_addSong.end(self, _r)

        def removeSong(self, id, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_removeSong.invoke(self, ((id, ), _ctx))

        def begin_removeSong(self, id, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_removeSong.begin(self, ((id, ), _response, _ex, _sent, _ctx))

        def end_removeSong(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_removeSong.end(self, _r)

        def listSongs(self, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_listSongs.invoke(self, ((), _ctx))

        def begin_listSongs(self, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_listSongs.begin(self, ((), _response, _ex, _sent, _ctx))

        def end_listSongs(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_listSongs.end(self, _r)

        def searchSongs(self, nameRegex, artistRegex, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_searchSongs.invoke(self, ((nameRegex, artistRegex), _ctx))

        def begin_searchSongs(self, nameRegex, artistRegex, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_searchSongs.begin(self, ((nameRegex, artistRegex), _response, _ex, _sent, _ctx))

        def end_searchSongs(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_searchSongs.end(self, _r)

        def playSong(self, id, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_playSong.invoke(self, ((id, ), _ctx))

        def begin_playSong(self, id, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_playSong.begin(self, ((id, ), _response, _ex, _sent, _ctx))

        def end_playSong(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_playSong.end(self, _r)

        def stopSong(self, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_stopSong.invoke(self, ((), _ctx))

        def begin_stopSong(self, _response=None, _ex=None, _sent=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3._op_stopSong.begin(self, ((), _response, _ex, _sent, _ctx))

        def end_stopSong(self, _r):
            return _M_ArchDistrib.InterfaceMP3._op_stopSong.end(self, _r)

        def checkedCast(proxy, facetOrCtx=None, _ctx=None):
            return _M_ArchDistrib.InterfaceMP3Prx.ice_checkedCast(proxy, '::ArchDistrib::InterfaceMP3', facetOrCtx, _ctx)
        checkedCast = staticmethod(checkedCast)

        def uncheckedCast(proxy, facet=None):
            return _M_ArchDistrib.InterfaceMP3Prx.ice_uncheckedCast(proxy, facet)
        uncheckedCast = staticmethod(uncheckedCast)

    _M_ArchDistrib._t_InterfaceMP3Prx = IcePy.defineProxy('::ArchDistrib::InterfaceMP3', InterfaceMP3Prx)

    _M_ArchDistrib._t_InterfaceMP3 = IcePy.defineClass('::ArchDistrib::InterfaceMP3', InterfaceMP3, -1, (), True, False, None, (), ())
    InterfaceMP3._ice_type = _M_ArchDistrib._t_InterfaceMP3

    InterfaceMP3._op_addSong = IcePy.Operation('addSong', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0), ((), IcePy._t_string, False, 0), ((), _M_ArchDistrib._t_DataSeq, False, 0)), (), None, ())
    InterfaceMP3._op_removeSong = IcePy.Operation('removeSong', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, False, 0),), (), None, ())
    InterfaceMP3._op_listSongs = IcePy.Operation('listSongs', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), _M_ArchDistrib._t_SongSeq, False, 0), ())
    InterfaceMP3._op_searchSongs = IcePy.Operation('searchSongs', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0), ((), IcePy._t_string, False, 0)), (), ((), _M_ArchDistrib._t_SongSeq, False, 0), ())
    InterfaceMP3._op_playSong = IcePy.Operation('playSong', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, False, 0),), (), None, ())
    InterfaceMP3._op_stopSong = IcePy.Operation('stopSong', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), None, ())

    _M_ArchDistrib.InterfaceMP3 = InterfaceMP3
    del InterfaceMP3

    _M_ArchDistrib.InterfaceMP3Prx = InterfaceMP3Prx
    del InterfaceMP3Prx

# End of module ArchDistrib
