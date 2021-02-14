package model

case class Row(val cells:Array[Table.Cell]){
  def prettyFormat(columnWidths:Seq[Int],colSeparator:String="|",lineEnd:String="\n"):StringBuffer = {
    require(columnWidths.length==cells.length)
    val sb = new StringBuffer()
    cells.zip(columnWidths).take(columnWidths.length-1).foreach {(cell,colWidth)=>
      val padding = " "*(colWidth - cell.toString.length)
      sb.append(cell).append(padding).append(colSeparator)
    }
    sb.append(cells.last).append(lineEnd)
    return sb
  }

}