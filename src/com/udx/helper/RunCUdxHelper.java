package com.udx.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.ngis.udx.EKernelType;
import com.ngis.udx.UdxDataset;
import com.ngis.udx.UdxKernelIntValue;
import com.ngis.udx.UdxKernelStringArray;
import com.ngis.udx.UdxKernelStringValue;
import com.ngis.udx.UdxNode;
import com.udx.util.RunCUtil;
import com.udx.util.RunCUtil.ParaControlsGroup;
import com.udx.util.StringUtil.AlignType;
import com.udx.util.UdxHandlUtil;
import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * Dr.Lu's Fvcom,step4,[input] [ch_run.dat] conversion Tool Fvcom程序运行控制文件
 * 
 * @author CK
 *
 */
public class RunCUdxHelper {

	public static UdxDataset RunCToUdx(String fileFullPath, String dllPaths)
			throws Exception {
		String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
		if (dllPaths != null) {
		} else {
			dllPaths = dllPath;
		}
		if (fileFullPath.equals("TestPath")) {
			fileFullPath = "D:\\Workspace\\FvCom\\testData\\run\\ch_run.dat";
		}
		UdxDataset udx_return_Dt = new UdxDataset(dllPaths);
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(
				fileFullPath)));
		String line = null;
		// ////////////////////////////////////////////////////////////////////////////////
		
//		UdxNode testNode=udx_return_Dt.addChildNode("StringArray", EKernelType.EKT_STRING, 3);
//		
//		for(int i=0;i<3;i++){
//			((UdxKernelStringArray)testNode.getKernel()).addTypedValue(String.valueOf(i));
//		}
//		
//		UdxNode printNode=udx_return_Dt.getChildNode(0);
//		
//		for(int i=0;i<3;i++){
//			String dataA=((UdxKernelStringArray)printNode.getKernel()).getTypedValueByIndex(i);
//			System.out.println(dataA+"\n");
//		}
		
		
		
		UdxNode CaseTitleNode = udx_return_Dt.addChildNode("CaseTitle",
				EKernelType.EKT_NODE, 1);
		UdxNode TimeIntegrationNode = udx_return_Dt.addChildNode(
				"TimeIntegration", EKernelType.EKT_NODE, 1);
		UdxNode InputOutputNode = udx_return_Dt.addChildNode("InputOutput",
				EKernelType.EKT_NODE, 1);
		UdxNode CallRho_meanNode = udx_return_Dt.addChildNode("CallRho_mean",
				EKernelType.EKT_NODE, 1);
		UdxNode OutputOfAverageDataFieldsNode = udx_return_Dt.addChildNode(
				"OutputOfAverageDataFields", EKernelType.EKT_NODE, 1);
		UdxNode BottomFrictionNode = udx_return_Dt.addChildNode(
				"BottomFriction", EKernelType.EKT_NODE, 1);
		UdxNode DiffusivityCoefficientsNode = udx_return_Dt.addChildNode(
				"DiffusivityCoefficients", EKernelType.EKT_NODE, 1);
		UdxNode ModelEquationsNode = udx_return_Dt.addChildNode(
				"ModelEquations", EKernelType.EKT_NODE, 1);
		UdxNode DensityCalculationNode = udx_return_Dt.addChildNode(
				"DensityCalculation", EKernelType.EKT_NODE, 1);
		UdxNode AtmosphericForcingNode = udx_return_Dt.addChildNode(
				"AtmosphericForcing", EKernelType.EKT_NODE, 1);
		UdxNode TempSalinityAdjustmentsNode = udx_return_Dt.addChildNode(
				"TempSalinityAdjustments", EKernelType.EKT_NODE, 1);
		UdxNode DepthAdjustmentsNode = udx_return_Dt.addChildNode(
				"DepthAdjustments", EKernelType.EKT_NODE, 1);
		UdxNode TidalForcingNode = udx_return_Dt.addChildNode("TidalForcing",
				EKernelType.EKT_NODE, 1);
		UdxNode StandardDepthLevelsNode = udx_return_Dt.addChildNode(
				"StandardDepthLevels", EKernelType.EKT_NODE, 1);
		UdxNode SigmaDistributionNode = udx_return_Dt.addChildNode(
				"SigmaDistribution", EKernelType.EKT_NODE, 1);
		UdxNode LagrangianTrackingNode = udx_return_Dt.addChildNode(
				"LagrangianTracking", EKernelType.EKT_NODE, 1);
		UdxNode TimeSeriesOutputNode = udx_return_Dt.addChildNode(
				"TimeSeriesOutput", EKernelType.EKT_NODE, 1);
		UdxNode WaterQualityModuleNode = udx_return_Dt.addChildNode(
				"WaterQualityModule", EKernelType.EKT_NODE, 1);
		UdxNode WettingDryingNode = udx_return_Dt.addChildNode("WettingDrying",
				EKernelType.EKT_NODE, 1);
		UdxNode SSTDataAssimilationNode = udx_return_Dt.addChildNode(
				"SSTDataAssimilation", EKernelType.EKT_NODE, 1);
		UdxNode CURRENT_DATA_ASSIMILATION_VARIABLESNode = udx_return_Dt
				.addChildNode("CURRENT_DATA_ASSIMILATION_VARIABLES",
						EKernelType.EKT_NODE, 1);
		UdxNode TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLESNode = udx_return_Dt
				.addChildNode(
						"TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES",
						EKernelType.EKT_NODE, 1);
		UdxNode VARIABLES_CONTROLLING_NETCDF_OUTPUTNode = udx_return_Dt
				.addChildNode("VARIABLES_CONTROLLING_NETCDF_OUTPUT",
						EKernelType.EKT_NODE, 1);
		UdxNode Richardson_dissipation_correctionNode = udx_return_Dt
				.addChildNode("Richardson_dissipation_correction",
						EKernelType.EKT_NODE, 1);
		UdxNode OpenBoundaryTempSaltNudgingNode = udx_return_Dt.addChildNode(
				"OpenBoundaryTempSaltNudging", EKernelType.EKT_NODE, 1);
		UdxNode OpenBoundaryTempSaltSeriesNudgingNode = udx_return_Dt
				.addChildNode("OpenBoundaryTempSaltSeriesNudging",
						EKernelType.EKT_NODE, 1);
		UdxNode VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUTNode = udx_return_Dt
				.addChildNode(
						"VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT",
						EKernelType.EKT_NODE, 1);
		UdxNode THE_TYPE_OF_TempSalt_OBCNode = udx_return_Dt.addChildNode(
				"THE_TYPE_OF_TempSalt_OBC", EKernelType.EKT_NODE, 1);
		UdxNode TidalOpenBoundaryOutputNode = udx_return_Dt.addChildNode(
				"TidalOpenBoundaryOutput", EKernelType.EKT_NODE, 1);
		UdxNode VARIABLES_For_SPECIFY_DYE_RELEASENode = udx_return_Dt
				.addChildNode("VARIABLES_For_SPECIFY_DYE_RELEASE",
						EKernelType.EKT_NODE, 1);
		UdxNode Sediment_moduleNode = udx_return_Dt.addChildNode(
				"Sediment_module", EKernelType.EKT_NODE, 1);

		do {
			String dataStr = null;
			if ((line = br.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) != ' '
						&& line.charAt(0) != '!') {
					if (line.indexOf('!') != -1) {
						dataStr = line.substring(0, line.indexOf('!'));
					} else {
						dataStr = line;
					}

				} else {
					continue;
				}
				dataStr = dataStr.trim();
				String parameterName, parameterValue = null;
				String[] dataArray = dataStr.split("=");
				if (dataArray.length == 2) {
					parameterName = dataArray[0].trim();
					parameterValue = dataArray[1].trim();
				} else {
					br.close();
					return null;
				}
				RunCUtil runUtil = new RunCUtil();
				switch (runUtil.getGroup(parameterName)) {
				case CaseTitle:
					UdxHandlUtil.createNode(CaseTitleNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TimeIntegration:
					UdxHandlUtil.createNode(TimeIntegrationNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case InputOutput:
					UdxHandlUtil.createNode(InputOutputNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case CallRho_mean:
					UdxHandlUtil.createNode(CallRho_meanNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case OutputOfAverageDataFields:
					UdxHandlUtil.createNode(OutputOfAverageDataFieldsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case BottomFriction:
					UdxHandlUtil.createNode(BottomFrictionNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case DiffusivityCoefficients:
					UdxHandlUtil.createNode(DiffusivityCoefficientsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case ModelEquations:
					UdxHandlUtil.createNode(ModelEquationsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case DensityCalculation:
					UdxHandlUtil.createNode(DensityCalculationNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case AtmosphericForcing:
					UdxHandlUtil.createNode(AtmosphericForcingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TempSalinityAdjustments:
					UdxHandlUtil.createNode(TempSalinityAdjustmentsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case DepthAdjustments:
					UdxHandlUtil.createNode(DepthAdjustmentsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TidalForcing:
					UdxHandlUtil.createNode(TidalForcingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case StandardDepthLevels:
					UdxHandlUtil.createNode(StandardDepthLevelsNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case SigmaDistribution:
					UdxHandlUtil.createNode(SigmaDistributionNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case LagrangianTracking:
					UdxHandlUtil.createNode(LagrangianTrackingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TimeSeriesOutput:
					UdxHandlUtil.createNode(TimeSeriesOutputNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case WaterQualityModule:
					UdxHandlUtil.createNode(WaterQualityModuleNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case WettingDrying:
					UdxHandlUtil.createNode(WettingDryingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case SSTDataAssimilation:
					UdxHandlUtil.createNode(SSTDataAssimilationNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case CURRENT_DATA_ASSIMILATION_VARIABLES:
					UdxHandlUtil.createNode(
							CURRENT_DATA_ASSIMILATION_VARIABLESNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES:
					UdxHandlUtil
							.createNode(
									TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLESNode,
									runUtil.getUdxDataType(parameterName),
									parameterName, parameterValue);
					break;
				case VARIABLES_CONTROLLING_NETCDF_OUTPUT:
					UdxHandlUtil.createNode(
							VARIABLES_CONTROLLING_NETCDF_OUTPUTNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case Richardson_dissipation_correction:
					UdxHandlUtil.createNode(
							Richardson_dissipation_correctionNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case OpenBoundaryTempSaltNudging:
					UdxHandlUtil.createNode(OpenBoundaryTempSaltNudgingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case OpenBoundaryTempSaltSeriesNudging:
					UdxHandlUtil.createNode(
							OpenBoundaryTempSaltSeriesNudgingNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT:
					UdxHandlUtil
							.createNode(
									VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUTNode,
									runUtil.getUdxDataType(parameterName),
									parameterName, parameterValue);
					break;
				case THE_TYPE_OF_TempSalt_OBC:
					UdxHandlUtil.createNode(THE_TYPE_OF_TempSalt_OBCNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case TidalOpenBoundaryOutput:
					UdxHandlUtil.createNode(TidalOpenBoundaryOutputNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case VARIABLES_For_SPECIFY_DYE_RELEASE:
					UdxHandlUtil.createNode(
							VARIABLES_For_SPECIFY_DYE_RELEASENode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				case Sediment_module:
					UdxHandlUtil.createNode(Sediment_moduleNode,
							runUtil.getUdxDataType(parameterName),
							parameterName, parameterValue);
					break;
				default:
					break;
				// br.close();
				// return null;
				}

			}
		} while (line != null);

		// dataGroup.
		// ////////////////////////////////////////////////////////////////////////////////
		br.close();
		return udx_return_Dt;
	}

	public static boolean RunCToUdx(String inputPath, String dllPaths,
			String SavePath) throws Exception {

		UdxDataset dataset = RunCToUdx(inputPath, dllPaths);
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\run\\ch_run.xml";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		File outputFile = new File(SavePath);
		if (!outputFile.exists()) {
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
		}
		if (dataset != null) {
			dataset.FormatToXmlFile(SavePath);
			return true;
		} else {
			System.out.println("dataset is null.");
			return false;
		}
	}

	public static String UdxToRunC(UdxDataset dataset) throws Exception {
		
		if (dataset != null) {
		} else {
			String dllPath = "E:\\DEVELOPERKIT\\Work\\JWorkSpace\\workspace2_cpp_java\\UdxHelper\\Library";
			UdxDataset udx_return_Dt = new UdxDataset(dllPath);
			String fileName = "D:\\Workspace\\FvCom\\tmpData\\run\\ch_run.xml";
			udx_return_Dt.LoadFromXmlFile(fileName);
			dataset = udx_return_Dt;
		}
		StringBuilder DataStr = new StringBuilder();
		////////////////////////////////////////////////////////////////////////////////////////
//		for(int i=0;i<3;i++){
//		String dataA=((UdxKernelStringArray)dataset.getChildNode(0).getKernel()).getTypedValueByIndex(i);
//		System.out.println(dataA+"\n");
//	}
		
		    RunCUtil runCUtil=new RunCUtil();
		    DataStr.append(runCUtil.getDescriptionText(ParaControlsGroup.TitleText));
		    DataStr.append("\n\n");
		    
		    Map<ParaControlsGroup,String> dataMap=new HashMap<ParaControlsGroup,String>();
		    
		    for(int i=0;i<dataset.getChildNodeCount();i++){
		    	UdxNode childNode=dataset.getChildNode(i);
		    	String oneNodeDataStr="";
		    	for(int j=0;j<childNode.getChildNodeCount();j++){
		    		oneNodeDataStr+=childNode.getChildNode(j).getName()+" =";
		    		oneNodeDataStr+= UdxHandlUtil.getStrFromUdxNode(childNode.getChildNode(j), 3, AlignType.None);
		    		oneNodeDataStr+="\n";
		    	}
		    	dataMap.put(ParaControlsGroup.valueOf(childNode.getName()), oneNodeDataStr);
		    }
		    
		    Map<ParaControlsGroup,String> descriptionMap=runCUtil.getDescriptionMap();
		   
		    for (ParaControlsGroup key : descriptionMap.keySet()) {  
		    	
		       if(dataMap.get(key)!=null&&descriptionMap.get(key)!=null){
		    	   DataStr.append(descriptionMap.get(key)+"\n");  
		    	   DataStr.append(dataMap.get(key)+"\n");
		       }		      
		    }  		
		////////////////////////////////////////////////////////////////////////////////////////
		
		return DataStr.toString();
	}

	public static boolean UdxToRunC(UdxDataset dataset, String SavePath)
			throws Exception {
		String DataStr = UdxToRunC(dataset);
		if (DataStr.equals("") || DataStr == null) {
			System.out.println("conversion failed.");
			return false;
		}
		if (SavePath == "TestPath") {
			SavePath = "D:\\Workspace\\FvCom\\tmpData\\run\\ch_run1.dat";
		} else if (SavePath.equals("") || SavePath == null) {
			System.out.println("the SavePath is not right.");
			return false;
		}
		File outputFile = new File(SavePath);
		if (!outputFile.exists()) {
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
		}

		try {
			File dataFile = new File(SavePath);
			if (dataFile.exists()) {
				dataFile.delete();
			}
			dataFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(dataFile));
			out.append(DataStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
