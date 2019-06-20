/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software
 * License version 1.1, a copy of which has been included with this
 * distribution in the LICENSE.APL file.  */

package org.apache.log4j.helpers;

/**
   SingleLineTracerPrintWriter overrides the println function in
   TracerPrintWriter by replacing the TAB character with spaces.
   It also does not print the "\n".
   <p>
   The default format generated by TracerPrintWriter for exceptions
   prints on multiple lines, which does not interact well with some
   logging systems. On the other hand, a stack-trace on one line can be a
   mite difficult to read, so this class should only be used where really
   necessary :-)
   <p>
   For syslog daemons, tabs in messages are not friendly, hence the
   replacement of tabs by spaces here. It shouldn't do any harm to
   do this for all messages...
   <p>
   Perhaps it might be better to enhance TracerPrintWriter to have
   a configuration item for one-line or multi-line mode...
*/
public class SingleLineTracerPrintWriter extends TracerPrintWriter {

  static String TAB = "    ";

  public SingleLineTracerPrintWriter(QuietWriter qWriter) {
    super(qWriter);
  }

  /**
     Make the first Exception line print properly by omitting the \n at the
     end.
  */
  public
   void println(Object o) {
    this.qWriter.write(o.toString());
  }

  // Note: the Char[] form is handled by the TracerPrinterWriter super
  // class

  /**
     Remove the first character from the string (usually a TAB) and do
     not print "\n"
  */   
  public
  void println(String s) {
      // remove '^I' and replace it with 4 spaces
      this.qWriter.write(TAB+s.substring(1));
  }
}