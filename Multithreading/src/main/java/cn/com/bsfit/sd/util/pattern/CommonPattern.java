package cn.com.bsfit.sd.util.pattern;

/**
 * @author:
 * @date: created in 16:12 2020/11/6
 * @version:
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommonPattern {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^([+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?)$");

    public static final Pattern NUM_COLLECTION_PATTERN = Pattern.compile("^([+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?)(,([+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?))+");

    public static final Pattern REGION_PATTERN = Pattern.compile("[\\[(][+-]?([0-9]*\\.?[0-9]+|\\.?[0-9]+|\\])]");

    public static final Pattern NUM_REGION_PATTERN = Pattern.compile("[\\[(][+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?,[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?[\\])]");

    public static final Pattern POSITION_PATTERN = Pattern.compile("^([(]([0-9]+\\.?[0-9]+|[0-9]+\\.?[0-9]*),([0-9]+\\.?[0-9]+|[0-9]+\\.?[0-9]*)[)]){1}$");

    public static final Pattern TIME_REGION_PATTERN = Pattern.compile("[\\[(][0-2]?\\d(:[0-5]\\d){0,2},[0-2]?\\d(:[0-5]\\d){0,2}[\\])]");

    public static final Pattern COLLECTION_PATTERN = Pattern.compile("[^,]+(,[^,]+)*");

    public static final Pattern STRING_PATTERN = Pattern.compile("([\\s\\S]+)");

    public static final Pattern BOOLEAN_PATTERN = Pattern.compile("(true|false)");

    public static final Pattern CURR_EXPRESSION_PATTERN = Pattern.compile("#.+?#");

    public static final Pattern ENG_NUM_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    public static final Pattern ENUM_NV_PATTERN = Pattern.compile("^[A-Za-z0-9_.]{1,100}-[^\\s-+]+$");

    public static final Pattern POSITIVE_NUM_PATTERN = Pattern.compile("[0-9]*");

    public static final Pattern ENG_NUM_ULN_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    public static final Pattern CN_EN_NUM_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9]+$");

    public static final Pattern CN_EN_NUM_ULN_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9_]+$");

    public static final Pattern IN_TIME_RANGE = Pattern.compile("^[\\[(](([0-1]\\d)|(2[0-3]))(:[0-5]\\d){1,2},(([0-1]\\d)|(2[0-3]))(:[0-5]\\d){1,2}[\\])]");

    public static final Pattern NUMBER_UNIT_PATTERN = Pattern.compile("^[0-9]+([.]{1}[0-9]+){0,1}$");

    public static final Pattern POSITIVE_INTEGER_PATTERN = Pattern.compile("\\d+");

    public static final Pattern WORKFLOW_INDI_PATTERN = Pattern.compile("@.*?@");

    public static final Pattern FILED_ANYONE_CHECK = Pattern.compile("^[A-Za-z0-9_]+$");

    public static final Pattern FILED_NAME_CHECK = Pattern.compile("^[a-z]+[_a-zA-Z0-9]*");

    public static final Pattern NUM_ANYWHERE = Pattern.compile("[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?");

    public static final Pattern IMP_EXP_VERSION = Pattern.compile("^7\\.[0-9]+\\..*");

    public static final Pattern RULE_LOGIC_PATTERN = Pattern.compile("^[0-9A-Z!&|()]*?");

    public static final Map<String, Pattern> PATTERN_MAP = new HashMap<>();

    static {
        PATTERN_MAP.put("number", NUMBER_PATTERN);
        PATTERN_MAP.put("numCollection", NUM_COLLECTION_PATTERN);
        PATTERN_MAP.put("region", REGION_PATTERN);
        PATTERN_MAP.put("collection", COLLECTION_PATTERN);
        PATTERN_MAP.put("string", STRING_PATTERN);
        PATTERN_MAP.put("boolean", BOOLEAN_PATTERN);
    }
}