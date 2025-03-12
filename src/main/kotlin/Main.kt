import org.apache.commons.csv.CSVFormat
import smile.data.DataFrame
import smile.io.Read
import smile.data.formula.Formula
import smile.validation.CrossValidation
import smile.regression.GradientTreeBoost

fun main() {
    val dsFileFormat = CSVFormat.DEFAULT.builder()
        .setHeader()
        .setSkipHeaderRecord(true)
        .setDelimiter(',')
        .build()

    val dataset: DataFrame = Read.csv(
        "C:\\Users\\Sofar\\Documents\\src\\main\\resources\\house_price_regression_dataset.csv",
        dsFileFormat
    )

    println("Исходный DataFrame:")
    println(dataset)


    // Выполняем регрессию, используя "Performance Index" как целевую переменную
    val formula = Formula.lhs("House_Price")
    val res = CrossValidation.regression(10, formula, dataset) { f, data ->
        GradientTreeBoost.fit(f, data)
    }
    println(res)
}
