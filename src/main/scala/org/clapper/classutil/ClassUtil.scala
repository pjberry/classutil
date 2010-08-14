/*
  ---------------------------------------------------------------------------
  This software is released under a BSD license, adapted from
  http://opensource.org/licenses/bsd-license.php

  Copyright (c) 2010, Brian M. Clapper
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

  * Neither the names "clapper.org", "ClassUtil", nor the names of its
    contributors may be used to endorse or promote products derived from
    this software without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
  IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  ---------------------------------------------------------------------------
*/

package org.clapper.classutil

/**
 * Some general-purpose class-related utility functions.
 */
object ClassUtil
{
    /**
     * Determine whether an object is a primitive or not.
     *
     * @param obj  the object
     *
     * @return `true` if its class is a primitive, `false` if not.
     */
    def isPrimitive(obj: Any): Boolean =
    {
        obj match
        {
            case _: Byte    => true
            case _: Short   => true
            case _: Int     => true
            case _: Long    => true
            case _: Float   => true
            case _: Double  => true
            case _: Char    => true
            case _: Boolean => true
            case _: Unit    => true
            case _          => false
        }
    }

    /**
     * Determine whether a class represents an underlying primitive or not.
     * For instance, `Int`, `Float` and `Unit` all represent underlying
     * primitives.
     *
     * @param cls  the class
     *
     * @return `true` if the class represents a primitive, `false` if not
     */
    def isPrimitive[T](cls: Class[T])(implicit man: Manifest[T]): Boolean =
    {
        import scala.reflect.ClassManifest

        def scalaPrimitive = man match
        {
            case ClassManifest.Boolean => true
            case ClassManifest.Byte    => true
            case ClassManifest.Char    => true
            case ClassManifest.Double  => true
            case ClassManifest.Float   => true
            case ClassManifest.Int     => true
            case ClassManifest.Long    => true
            case ClassManifest.Short   => true
            case ClassManifest.Unit    => true
            case _ => false
        }

        def javaPrimitive = cls.getName match
        {
            // I'm sure there's a less obnoxious way to do this...

            case "java.lang.Boolean"   => true
            case "java.lang.Byte"      => true
            case "java.lang.Character" => true
            case "java.lang.Double"    => true
            case "java.lang.Float"     => true
            case "java.lang.Integer"   => true
            case "java.lang.Long"      => true
            case "java.lang.Short"     => true
            case "java.lang.Void"      => true
            case _                     => false
        }

        scalaPrimitive || javaPrimitive
    }
}