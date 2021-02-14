package model
import scala.util.Try
import scala.collection.mutable.{ArrayBuffer => MArrayBuffer}
import os.Path

trait ITable {
  def prityPrint():Unit
}

case class Table(columnWidths:Seq[Int],hasHeader:Boolean=true,rows:Seq[Row],rowSeparator:String="-",lineEnd:String="\n") extends ITable {
  def prityPrint():Unit = {
    val header = rows.headOption.flatMap{row => if hasHeader then Some(row) else None}.fold(StringBuffer("")){row=>
      val sb =StringBuffer()
      sb.append(row.prettyFormat(columnWidths))
      sb.append(columnWidths.map(width=>"="*width).mkString("+")).append(lineEnd)
      sb
    }
    val styled = rows.drop(if hasHeader then 1 else 0).take(rows.length - 2).foldLeft(StringBuffer("")){(acc,row)=>
      acc.append(row.prettyFormat(columnWidths))
      val rowSep = columnWidths.map(width=> rowSeparator*width).mkString("+")
      acc.append(rowSep).append(lineEnd)
      acc
    }.append(rows.last.prettyFormat(columnWidths)).append(lineEnd)
    print(header.append(styled))
  }
}

object Table {
  opaque type Cell = String

  object Cell {
    def apply(s:String):Table.Cell = s
  }

}



case class Header(cells: Seq[Table.Cell])
