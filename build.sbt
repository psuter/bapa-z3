name := "bapa-z3"

version := "1.0"

organization := "ch.epfl.lara"

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

unmanagedBase <<= baseDirectory { base => base / "unmanaged" }

fork in run := true

fork in test := true
