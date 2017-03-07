package com.udx.util;

import java.util.HashMap;
import java.util.Map;

import com.udx.util.UdxHandlUtil.UdxDataType;

/**
 * 由于ch_run.dat控制参数较多，此类用于对其进行处理
 * @author CK
 *
 */
public class RunCUtil {
	private Map<String,ParaControlsGroup> parameterMap;
	private Map<String,UdxDataType> udxDataTypeMap;
	private Map<ParaControlsGroup,String> descriptionMap;
	/**
	 * 参数控制分组
	 * @author CK
	 *
	 */
	public static enum ParaControlsGroup{
		                   TitleText,
		                   CaseTitle,
		                   TimeIntegration,
		                   InputOutput,
		                   CallRho_mean,
		                   OutputOfAverageDataFields,
		                   BottomFriction,
		                   DiffusivityCoefficients,
		                   ModelEquations,
		                   DensityCalculation,
		                   AtmosphericForcing,
		                   TempSalinityAdjustments,
		                   DepthAdjustments,
		                   TidalForcing,
		                   StandardDepthLevels,
		                   SigmaDistribution,
		                   LagrangianTracking,
		                   TimeSeriesOutput,
		                   WaterQualityModule,
		                   WettingDrying,
		                   SSTDataAssimilation,
		                   CURRENT_DATA_ASSIMILATION_VARIABLES,
		                   TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES,
		                   VARIABLES_CONTROLLING_NETCDF_OUTPUT,
		                   Richardson_dissipation_correction,
		                   OpenBoundaryTempSaltNudging,
		                   OpenBoundaryTempSaltSeriesNudging,
		                   VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT,
		                   THE_TYPE_OF_TempSalt_OBC,
		                   TidalOpenBoundaryOutput,
		                   VARIABLES_For_SPECIFY_DYE_RELEASE,
		                   Sediment_module		                   		                   
		                   }
	
	public RunCUtil(){
		parameterMap=new HashMap<String,ParaControlsGroup>();
		parameterMap.put("CASETITLE", ParaControlsGroup.CaseTitle);		
		
		parameterMap.put("DTE", ParaControlsGroup.TimeIntegration);
		parameterMap.put("ISPLIT", ParaControlsGroup.TimeIntegration);
		parameterMap.put("IRAMP", ParaControlsGroup.TimeIntegration);
		parameterMap.put("NSTEPS", ParaControlsGroup.TimeIntegration);
		
		parameterMap.put("INPDIR", ParaControlsGroup.InputOutput);
		parameterMap.put("OUTDIR", ParaControlsGroup.InputOutput);
		parameterMap.put("INFOFILE", ParaControlsGroup.InputOutput);
		parameterMap.put("IREPORT", ParaControlsGroup.InputOutput);
		parameterMap.put("IRECORD", ParaControlsGroup.InputOutput);
		parameterMap.put("IDMPSMS", ParaControlsGroup.InputOutput);
		parameterMap.put("IRESTART", ParaControlsGroup.InputOutput);
		parameterMap.put("RESTART", ParaControlsGroup.InputOutput);
		
		parameterMap.put("IRHO_MEAN", ParaControlsGroup.CallRho_mean);
		
		parameterMap.put("AVGE_ON", ParaControlsGroup.OutputOfAverageDataFields);
		parameterMap.put("INT_AVGE", ParaControlsGroup.OutputOfAverageDataFields);
		parameterMap.put("BEG_AVGE", ParaControlsGroup.OutputOfAverageDataFields);
		parameterMap.put("NUM_AVGE", ParaControlsGroup.OutputOfAverageDataFields);
		parameterMap.put("CDF_OUT_AVE", ParaControlsGroup.OutputOfAverageDataFields);
		parameterMap.put("CDF_VDP_AVE", ParaControlsGroup.OutputOfAverageDataFields);
		
		parameterMap.put("BFRIC", ParaControlsGroup.BottomFriction);
		parameterMap.put("Z0B", ParaControlsGroup.BottomFriction);
		parameterMap.put("BROUGH_TYPE", ParaControlsGroup.BottomFriction);
		
		parameterMap.put("HORZMIX", ParaControlsGroup.DiffusivityCoefficients);
		parameterMap.put("HORCON", ParaControlsGroup.DiffusivityCoefficients);
		parameterMap.put("HPRNU", ParaControlsGroup.DiffusivityCoefficients);
		parameterMap.put("VERTMIX", ParaControlsGroup.DiffusivityCoefficients);
		parameterMap.put("UMOL", ParaControlsGroup.DiffusivityCoefficients);
		parameterMap.put("VPRNU", ParaControlsGroup.DiffusivityCoefficients);
		
		parameterMap.put("BAROTROPIC", ParaControlsGroup.ModelEquations);
		parameterMap.put("SALINITY_ON", ParaControlsGroup.ModelEquations);
		parameterMap.put("TEMP_ON", ParaControlsGroup.ModelEquations);
		
		parameterMap.put("C_BAROPG", ParaControlsGroup.DensityCalculation);
		parameterMap.put("CTRL_DEN", ParaControlsGroup.DensityCalculation);
		parameterMap.put("VERT_STAB", ParaControlsGroup.DensityCalculation);
		
		parameterMap.put("H_TYPE", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("M_TYPE", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("WINDTYPE", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("ZETA1", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("ZETA2", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("RHEAT", ParaControlsGroup.AtmosphericForcing);
		parameterMap.put("THOUR_HS", ParaControlsGroup.AtmosphericForcing);
		
		parameterMap.put("TS_FCT", ParaControlsGroup.TempSalinityAdjustments);
		
		parameterMap.put("DJUST", ParaControlsGroup.DepthAdjustments);
		parameterMap.put("MIN_DEPTH", ParaControlsGroup.DepthAdjustments);
		
		parameterMap.put("S_TYPE", ParaControlsGroup.TidalForcing);
		parameterMap.put("DELTT", ParaControlsGroup.TidalForcing);
		
		parameterMap.put("KSL", ParaControlsGroup.StandardDepthLevels);
		parameterMap.put("DPTHSL", ParaControlsGroup.StandardDepthLevels);
		
		parameterMap.put("P_SIGMA", ParaControlsGroup.SigmaDistribution);
		parameterMap.put("KB", ParaControlsGroup.SigmaDistribution);
		
		parameterMap.put("LAG_ON", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_INTERVAL", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_SCAL", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_COLD_START", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_INPFILE", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_RESFILE", ParaControlsGroup.LagrangianTracking);
		parameterMap.put("LAG_OUTFILE", ParaControlsGroup.LagrangianTracking);
		
		parameterMap.put("PROBE_ON", ParaControlsGroup.TimeSeriesOutput);
		
		parameterMap.put("WQM_ON", ParaControlsGroup.WaterQualityModule);
		parameterMap.put("NB", ParaControlsGroup.WaterQualityModule);
		parameterMap.put("BENWQM_KEY", ParaControlsGroup.WaterQualityModule);
		
		parameterMap.put("WET_DRY_ON", ParaControlsGroup.WettingDrying);
		
		parameterMap.put("SST_ASSIM", ParaControlsGroup.SSTDataAssimilation);
		parameterMap.put("RAD_SST", ParaControlsGroup.SSTDataAssimilation);
		parameterMap.put("GAMA_SST", ParaControlsGroup.SSTDataAssimilation);
		parameterMap.put("GALPHA_SST", ParaControlsGroup.SSTDataAssimilation);
		parameterMap.put("ASTIME_WINDOW_SST", ParaControlsGroup.SSTDataAssimilation);
		parameterMap.put("IAV_DAY", ParaControlsGroup.SSTDataAssimilation);
		
		parameterMap.put("CURRENT_ASSIM", ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("RAD_CUR", ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("GAMA_CUR", ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("GALPHA_CUR", ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("ASTIME_WINDOW_CUR", ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES);
		
		parameterMap.put("TS_ASSIM", ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("RAD_TS", ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("GAMA_TS", ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("GALPHA_TS", ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES);
		parameterMap.put("ASTIME_WINDOW_TS", ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES);
		
		parameterMap.put("CDF_OUT", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		parameterMap.put("CDF_INT", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		parameterMap.put("CDF_STK", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		parameterMap.put("CDF_VDP", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		parameterMap.put("INFO1", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		parameterMap.put("INFO2", ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT);
		
		parameterMap.put("SURFACEWAVE_MIX", ParaControlsGroup.Richardson_dissipation_correction);
		
		parameterMap.put("TS_NUDGING_OBC", ParaControlsGroup.OpenBoundaryTempSaltNudging);
		parameterMap.put("ALPHA_OBC", ParaControlsGroup.OpenBoundaryTempSaltNudging);
		
		parameterMap.put("TSOBC_ON", ParaControlsGroup.OpenBoundaryTempSaltSeriesNudging);
		parameterMap.put("ALPHA_SERIES_OBC", ParaControlsGroup.OpenBoundaryTempSaltSeriesNudging);
		
		parameterMap.put("OUT_BALANCE", ParaControlsGroup.VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT);
		parameterMap.put("NUM_BALANCE", ParaControlsGroup.VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT);
		parameterMap.put("NO_CELL", ParaControlsGroup.VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT);
		
		parameterMap.put("TYPE_TSOBC", ParaControlsGroup.THE_TYPE_OF_TempSalt_OBC);
		
		parameterMap.put("TIDE_INITIAL", ParaControlsGroup.TidalOpenBoundaryOutput);
		parameterMap.put("TIDE_INTERVAL", ParaControlsGroup.TidalOpenBoundaryOutput);
		
		parameterMap.put("DYE_ON", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("IINT_SPE_DYE_B", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("IINT_SPE_DYE_E", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("KSPE_DYE", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("K_SPECIFY", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("MSPE_DYE", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("M_SPECIFY", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		parameterMap.put("DYE_SOURCE_TERM", ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE);
		
		parameterMap.put("SEDIMENT_ON", ParaControlsGroup.Sediment_module);
		parameterMap.put("RESTART_SED", ParaControlsGroup.Sediment_module);
		
		
		
        udxDataTypeMap=new HashMap<String,UdxDataType>();
		udxDataTypeMap.put("CASETITLE", UdxDataType.UdxKernelStringValue);		
		
		udxDataTypeMap.put("DTE", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("ISPLIT", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("IRAMP", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("NSTEPS", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("INPDIR", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("OUTDIR", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("INFOFILE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("IREPORT", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("IRECORD", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("IDMPSMS", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("IRESTART", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("RESTART", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("IRHO_MEAN", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("AVGE_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("INT_AVGE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("BEG_AVGE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("NUM_AVGE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("CDF_OUT_AVE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("CDF_VDP_AVE", UdxDataType.UdxKernelStringArray);
		
		udxDataTypeMap.put("BFRIC", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("Z0B", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("BROUGH_TYPE", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("HORZMIX", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("HORCON", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("HPRNU", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("VERTMIX", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("UMOL", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("VPRNU", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("BAROTROPIC", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("SALINITY_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("TEMP_ON", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("C_BAROPG", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("CTRL_DEN", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("VERT_STAB", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("H_TYPE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("M_TYPE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("WINDTYPE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("ZETA1", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("ZETA2", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("RHEAT", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("THOUR_HS", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("TS_FCT", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("DJUST", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("MIN_DEPTH", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("S_TYPE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("DELTT", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("KSL", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("DPTHSL", UdxDataType.UdxKernelRealArray);
		
		udxDataTypeMap.put("P_SIGMA", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("KB", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("LAG_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("LAG_INTERVAL", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("LAG_SCAL", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("LAG_COLD_START", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("LAG_INPFILE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("LAG_RESFILE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("LAG_OUTFILE", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("PROBE_ON", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("WQM_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("NB", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("BENWQM_KEY", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("WET_DRY_ON", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("SST_ASSIM", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("RAD_SST", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GAMA_SST", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GALPHA_SST", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("ASTIME_WINDOW_SST", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("IAV_DAY", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("CURRENT_ASSIM", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("RAD_CUR", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GAMA_CUR", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GALPHA_CUR", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("ASTIME_WINDOW_CUR", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("TS_ASSIM", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("RAD_TS", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GAMA_TS", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("GALPHA_TS", UdxDataType.UdxKernelRealValue);
		udxDataTypeMap.put("ASTIME_WINDOW_TS", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("CDF_OUT", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("CDF_INT", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("CDF_STK", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("CDF_VDP", UdxDataType.UdxKernelStringArray);
		udxDataTypeMap.put("INFO1", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("INFO2", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("SURFACEWAVE_MIX", UdxDataType.UdxKernelStringValue);
		
		udxDataTypeMap.put("TS_NUDGING_OBC", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("ALPHA_OBC", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("TSOBC_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("ALPHA_SERIES_OBC", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("OUT_BALANCE", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("NUM_BALANCE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("NO_CELL", UdxDataType.UdxKernelIntArray);
		
		udxDataTypeMap.put("TYPE_TSOBC", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("TIDE_INITIAL", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("TIDE_INTERVAL", UdxDataType.UdxKernelIntValue);
		
		udxDataTypeMap.put("DYE_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("IINT_SPE_DYE_B", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("IINT_SPE_DYE_E", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("KSPE_DYE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("K_SPECIFY", UdxDataType.UdxKernelIntArray);
		udxDataTypeMap.put("MSPE_DYE", UdxDataType.UdxKernelIntValue);
		udxDataTypeMap.put("M_SPECIFY", UdxDataType.UdxKernelIntArray);
		udxDataTypeMap.put("DYE_SOURCE_TERM", UdxDataType.UdxKernelRealValue);
		
		udxDataTypeMap.put("SEDIMENT_ON", UdxDataType.UdxKernelStringValue);
		udxDataTypeMap.put("RESTART_SED", UdxDataType.UdxKernelStringValue);
		
		
		descriptionMap=new HashMap<ParaControlsGroup,String>();
		descriptionMap.put(ParaControlsGroup.TitleText, "!==============================================================================!\n"+
				"!   INPUT FILE FOR PARAMETERS CONTROLLING EXECUTION OF FVCOM                   !\n"+
				"!   DESCRIPTION OF VARIABLES AND SUGGESTED PARAMETERS CAN BE FOUND AT BOTTOM   !\n"+
				"!                                                                              !\n"+
				"!        FORMAT:			                                       !             \n"+
				"!       1.) VARIABLE  = VALUE  (EQUAL SIGN MUST BE USED)                       !\n"+
				"!       2.) FLOATING POINT VARIABLES MUST CONTAIN A PERIOD \".\" EX: 1.3, 2.,etc !\n"+
				"!       3.) BLANK LINES ARE IGNORED AS ARE LINES BEGINNING WITH ! (F90 COMMENT)!\n"+
				"!       4.) COMMENTS CAN FOLLOW VALUES IF MARKED BY !                          !\n"+
				"!       5.) ORDER OF VARIABLES IS NOT IMPORTANT                                !\n"+
				"!       6.) FOR MULTIPLE VALUE VARIABLES FIRST ENTRY IS NUMBER OF VARIABLES    !\n"+
				"!           TO FOLLOW (OR 0 IF NONE)                                           !\n"+
				"!       7.) DO NOT USE COMMAS TO SEPARATE VARIABLES                            !\n"+
				"!       8.) DO NOT EXCEED EIGHTY CHARACTERS PER LINE                           !\n"+
				"!       9.) FOR LINE CONTINUATION ADD \\ TO END OF LINE TO FORCE CONTINUE      !\n"+
				"!           TO NEXT LINE.  MAXIMUM 4 CONTINUATIONS                             !\n"+
				"!       10.) TRUE = T, FALSE = F                                               !\n"+
				"!                                                                              !\n"+ 
				"!  THE PREVIOUS FORMAT OF \"VARIABLE: VALUE\" IS NO LONGER VALID                 !\n"+
				"!  THE MORE ATTRACTIVE \" = \" FORMAT WAS SUGGESTED BY Hernan G. Arango          !\n"+
				"!    AND SUBSEQUENTLY ADOPTED                                                  !\n"+
				"!==============================================================================!\n");
		
		descriptionMap.put(ParaControlsGroup.CaseTitle,
				"!============ Case Title========================================================\n");
		
		descriptionMap.put(ParaControlsGroup.TimeIntegration,
				"!=========Parameters Controlling Time Integration===============================\n");
		
		descriptionMap.put(ParaControlsGroup.InputOutput,
				"!=========Parameters Controlling Input/Output===================================\n");
		
		descriptionMap.put(ParaControlsGroup.CallRho_mean,
				"!--Parameters Controlling call rho_mean-----------------------------------!\n");
		
		descriptionMap.put(ParaControlsGroup.OutputOfAverageDataFields,
				"!=========Parameters Controlling Output of Average Data Fields==================\n");
		
		descriptionMap.put(ParaControlsGroup.BottomFriction,
				"!=========Parameters Controlling Bottom Friction===============================!\n");
		
		descriptionMap.put(ParaControlsGroup.DiffusivityCoefficients,
				"!=========Parameters Controlling Diffusivity Coefficients======================!\n");
		
		descriptionMap.put(ParaControlsGroup.ModelEquations,
				"!=========Parameters Controlling Model Equations================================\n");
		
		descriptionMap.put(ParaControlsGroup.DensityCalculation,
				"!=========Parameters Controlling Density Calculation============================\n");
		
		descriptionMap.put(ParaControlsGroup.AtmosphericForcing,
				"!=========Parameters Controlling Atmospheric Forcing============================\n");
		
		descriptionMap.put(ParaControlsGroup.TempSalinityAdjustments,
				"!=========Parameters Controlling Temp/Salinity Adjustments======================\n");
		
		descriptionMap.put(ParaControlsGroup.DepthAdjustments,
				"!=========Parameters Controlling Depth Adjustments==============================\n");
		
		descriptionMap.put(ParaControlsGroup.TidalForcing,
				"!=========Parameters Controlling Tidal Forcing==================================\n");
		
		descriptionMap.put(ParaControlsGroup.StandardDepthLevels,
				"!=========Standard Depth Levels=================================================\n");
		
		descriptionMap.put(ParaControlsGroup.SigmaDistribution,
				"!============ Parameters Controlling Sigma Distribution=========================\n");
		
		descriptionMap.put(ParaControlsGroup.LagrangianTracking,
				"!============ Parameters Controlling Lagrangian Tracking========================\n");
		
		descriptionMap.put(ParaControlsGroup.TimeSeriesOutput,
				"!============ Parameters Controlling Time Series Output=========================\n");
		
		descriptionMap.put(ParaControlsGroup.WaterQualityModule,
				"!============ Parameters Controlling Water Quality Module=======================\n");
		
		descriptionMap.put(ParaControlsGroup.WettingDrying,
				"!============ Parameters Controlling Wetting/Drying=============================\n");
		
		descriptionMap.put(ParaControlsGroup.SSTDataAssimilation,
				"!============ Parmaeters Controlling SST Data Assimilation======================\n");
		
		
		descriptionMap.put(ParaControlsGroup.CURRENT_DATA_ASSIMILATION_VARIABLES,
				"!====CURRENT DATA ASSIMILATION VARIABLES========================================\n");
		
		descriptionMap.put(ParaControlsGroup.TEMPERATURE_SALINITY_DATA_ASSIMILATION_VARIABLES,
				"!====TEMPERATURE/SALINITY DATA ASSIMILATION VARIABLES===========================\n");
		
		descriptionMap.put(ParaControlsGroup.VARIABLES_CONTROLLING_NETCDF_OUTPUT,
				"!====VARIABLES CONTROLLING NETCDF OUTPUT========================================\n");
		
		descriptionMap.put(ParaControlsGroup.Richardson_dissipation_correction,
				"!==== Parameter Controlling Richardson # dep. dissipation correction============\n");
		
		descriptionMap.put(ParaControlsGroup.OpenBoundaryTempSaltNudging,
				"!==== Parameter Controlling Open Boundary Temp/Salt Nudging=====================\n");
		
		descriptionMap.put(ParaControlsGroup.OpenBoundaryTempSaltSeriesNudging,
				"!==== Parameter controlling Open Boundary Temp/Salt Series Nudging===========\n");
		
		descriptionMap.put(ParaControlsGroup.VARIABLES_CONTROLLING_2D_MOMENTUM_BALANCE_CHECKING_OUTOUT,
				"!=====VARIABLES CONTROLLING 2D MOMENTUM BALANCE CHECKING OUTOUT=======\n");
		
		descriptionMap.put(ParaControlsGroup.THE_TYPE_OF_TempSalt_OBC,
				"!=====PARAMETER CONTROLLING THE TYPE OF Temp/Salt OBC=======\n");
		descriptionMap.put(ParaControlsGroup.TidalOpenBoundaryOutput,
				"!==== Parameter controlling Tidal Open Boundary Output===========\n");
		descriptionMap.put(ParaControlsGroup.VARIABLES_For_SPECIFY_DYE_RELEASE,
				"!=====VARIABLES for SPECIFY DYE RELEASE==========\n");
		descriptionMap.put(ParaControlsGroup.Sediment_module,
				"!==============================Sediment module=============================\n");
		
				
	}
	public  ParaControlsGroup getGroup(String parameterName){
			
		return (ParaControlsGroup)parameterMap.get(parameterName);
		
	}
	public UdxDataType getUdxDataType(String parameterName){
		return (UdxDataType) udxDataTypeMap.get(parameterName);
	}
	public String getDescriptionText(ParaControlsGroup paraControlsGroup){
		return (String)descriptionMap.get(paraControlsGroup);
	}
	
	public Map<String,ParaControlsGroup> getParameterMap(){
		return  parameterMap;
	}
	public Map<ParaControlsGroup,String> getDescriptionMap(){
		return descriptionMap;
	}
	
	public Map<String,UdxDataType> getUdxDataTypeMap(){
		return udxDataTypeMap;
	}
}
