package com.codetlin.result;

import com.codetlin.xmlentity.Checkstyle;
import com.codetlin.xmlentity.Error;
import com.codetlin.xmlentity.FileXML;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Arrays;


public class ResultCalculator
{
    private static final String[] errors = {"DuplicateCaseInWhenExpression", "EqualsAlwaysReturnsTrueOrFalse", "EqualsWithHashCodeExist", "UnreachableCode",
            "ExplicitGarbageCollectionCall", "UnsafeCast", "UnconditionalJumpStatementInLoop", "WrongEqualsTypeParameter"};

    private static final String[] performance = {"ForEachOnRange", "SpreadOperator", "UnnecessaryTemporaryInstantiation"};

    private static final String[] complexity = {"LongMethod", "NestedBlockDepth", "LongParameterList", "LargeClass", "ComplexMethod", "TooManyFunctions",
            "ComplexCondition"};

    private static final double STYLE_WEIGHT = 0.1;
    private static final double PERFORMANCE_WEIGHT = 0.2;
    private static final double COMPLEXITY_WEIGHT = 0.35;
    private static final double ERROR_WEIGHT = 0.35;

    private int styleRating;
    private int performanceRating;
    private int complexityRating;
    private int errorRating;
    private int filesCount;

    public int getStyleRating()
    {
        return styleRating;
    }

    public int getPerformanceRating()
    {
        return performanceRating;
    }

    public int getComplexityRating()
    {
        return complexityRating;
    }

    public int getErrorRating()
    {
        return errorRating;
    }

    public int getOverallRating()
    {
        return overallRating;
    }

    private int overallRating;

    private Checkstyle root;

    public static void main(String[] args) throws Exception
    {
        File file = new File("C:\\Users\\verat\\AppData\\Local\\Temp\\uploadedFiles8938220954884981255\\detekt-checkstyle.xml");

        Serializer serializer = new Persister();
        Checkstyle check = serializer.read(Checkstyle.class, file);
    }

    public void calculateRatings(File file) throws Exception
    {
        Serializer serializer = new Persister();
        root = serializer.read(Checkstyle.class, file);

        styleRating = styleRating();
        performanceRating = performanceRating();
        errorRating = errorRating();
        complexityRating = complexityRating();

        overallRating = overallRating();
    }

    private int overallRating()
    {
        double result =  (COMPLEXITY_WEIGHT * complexityRating + STYLE_WEIGHT * styleRating
                + PERFORMANCE_WEIGHT * performanceRating + ERROR_WEIGHT * errorRating);
        return (int) Math.round(result);
    }

    private int errorRating()
    {
        double ratio = (double) otherErrorsCount(errors)/filesCount;
        return calculateRating(ratio);
    }

    private int calculateRating(double ratio)
    {
        int rating;
        if(ratio < 0.1)
            rating = 5;
        else if(ratio >= 0.1 && ratio < 0.3)
            rating = 4;
        else if(ratio >= 0.3 && ratio < 0.5)
            rating = 3;
        else
            rating = 2;
        return rating;
    }

    private int complexityRating()
    {
        double ratio = (double) otherErrorsCount(complexity)/filesCount;
        return calculateRating(ratio);
    }
    private int styleRating()
    {
        double ratio = (double) styleCount()/filesCount;
        return calculateRating(ratio);
    }
    private int performanceRating()
    {
        double ratio = (double) otherErrorsCount(performance)/filesCount;
        return calculateRating(ratio);
    }

    private int otherErrorsCount(String[] errorsList)
    {
        int count = 0;
        for(FileXML file : root.files)
        {
            for(Error err : file.errors)
            {
                if(Arrays.asList(errorsList).contains(err.source.substring(7)))
                {
                    count++;
                }
            }
        }
        return count;
    }
    private int styleCount()
    {
        int count = 0;
        for(FileXML file : root.files)
        {
            for(Error err : file.errors)
            {
                String error = err.source.substring(7);
                if(!Arrays.asList(complexity).contains(error) && !Arrays.asList(performance).contains(error) &&!Arrays.asList(errors).contains(error) )
                {
                    count++;
                }
            }
        }
        return count;
    }
}
