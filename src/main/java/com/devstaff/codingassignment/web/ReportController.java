package com.devstaff.codingassignment.web;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devstaff.codingassignment.entity.Farm;
import com.devstaff.codingassignment.service.FarmService;

@Controller
public class ReportController {
	@Autowired
	private FarmService farmService;
	
	@RequestMapping("showReport")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head><style>\r\n"
				+ "table {\r\n"
				+ "  font-family: arial, sans-serif;\r\n"
				+ "  border-collapse: collapse;\r\n"
				+ "  width: 100%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "td, th {\r\n"
				+ "  border: 1px solid #dddddd;\r\n"
				+ "  text-align: left;\r\n"
				+ "  padding: 8px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "tr:nth-child(even) {\r\n"
				+ "  background-color: #dddddd;\r\n"
				+ "}</style>");
		sb.append("<title> My First HTML Page - Changed</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2>Farm Collector Report</h2>");
		sb.append("<table>\r\n"
				+ "  <tr>\r\n"
				+ "    <th>Farm (Acres)</th>\r\n"
				+ "    <th>Crop Type</th>\r\n"
				+ "    <th>Season</th>\r\n"
				+ "    <th>Expected Harvest</th>\r\n"
				+ "    <th>Actual Harvest</th>\r\n"
				+ "  </tr>");
		List<Farm> farmList = farmService.getAllFarm();
		for (Farm farmEntry:farmList) {
			sb.append("<tr>\r\n"
					+ "    <td>"+farmEntry.getAcres()+"</td>\r\n"
					+ "    <td>"+farmEntry.getCropType()+"</td>\r\n"
					+ "    <td>"+farmEntry.getSeason()+"</td>\r\n"
					+ "    <td>"+farmEntry.getPlantedCount()+"</td>\r\n"		
					+ "    <td>"+farmEntry.getHarvested()+"</td>\r\n"
					+ "  </tr>");
			
		}
		sb.append("</table></body>");
		sb.append("</html>");
		
		return sb.toString();
	}
}
