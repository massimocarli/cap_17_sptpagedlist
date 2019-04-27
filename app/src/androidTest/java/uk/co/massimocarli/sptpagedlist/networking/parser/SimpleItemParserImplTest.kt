package uk.co.massimocarli.sptpagedlist.networking.parser

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

val EMPTY_ITEM_JSON = """
{
    "items": [],
    "page": 0,
    "length": 10
}
""".trimIndent()

val SINGLE_ITEM_JSON = """
{
    "items": [
        {
            "id": 17,
            "name": "Item #17",
            "description": "This is the Item number 17",
            "qta": 37
        }
    ],
    "page": 1,
    "length": 20
}
""".trimIndent()

val MULTIPLE_ITEM_JSON = """
{
    "items": [
        {
            "id": 0,
            "name": "Item #0",
            "description": "This is the Item number 0",
            "qta": 0
        },
        {
            "id": 1,
            "name": "Item #1",
            "description": "This is the Item number 1",
            "qta": 1
        },
        {
            "id": 2,
            "name": "Item #2",
            "description": "This is the Item number 2",
            "qta": 2
        },
        {
            "id": 3,
            "name": "Item #3",
            "description": "This is the Item number 3",
            "qta": 3
        },
        {
            "id": 4,
            "name": "Item #4",
            "description": "This is the Item number 4",
            "qta": 4
        },
        {
            "id": 5,
            "name": "Item #5",
            "description": "This is the Item number 5",
            "qta": 5
        },
        {
            "id": 6,
            "name": "Item #6",
            "description": "This is the Item number 6",
            "qta": 6
        },
        {
            "id": 7,
            "name": "Item #7",
            "description": "This is the Item number 7",
            "qta": 7
        },
        {
            "id": 8,
            "name": "Item #8",
            "description": "This is the Item number 8",
            "qta": 8
        },
        {
            "id": 9,
            "name": "Item #9",
            "description": "This is the Item number 9",
            "qta": 9
        }
    ],
    "page": 3,
    "length": 30
}
""".trimIndent()

class SimpleItemParserImplTest {

  lateinit var itemParser: SimpleItemParserImpl

  @Before
  fun setUp() {
    itemParser = SimpleItemParserImpl()
  }

  @Test
  fun parse_empty_listIsEmpty() {
    val result = itemParser.parse(EMPTY_ITEM_JSON)
    Truth.assertThat(result.page).isEqualTo(0)
    Truth.assertThat(result.length).isEqualTo(10)
    Truth.assertThat(result.items.size).isEqualTo(0)
  }

  @Test
  fun parse_singleElement_listContainsSingle() {
    val result = itemParser.parse(SINGLE_ITEM_JSON)
    Truth.assertThat(result.page).isEqualTo(1)
    Truth.assertThat(result.length).isEqualTo(20)
    Truth.assertThat(result.items.size).isEqualTo(1)
    val item = result.items[0]
    Truth.assertThat(item.id).isEqualTo(17)
    Truth.assertThat(item.name).isEqualTo("Item #17")
    Truth.assertThat(item.description).isEqualTo("This is the Item number 17")
    Truth.assertThat(item.qta).isEqualTo(37)
  }

  @Test
  fun parse_multipleElements_listContainsAll() {
    val result = itemParser.parse(MULTIPLE_ITEM_JSON)
    Truth.assertThat(result.page).isEqualTo(3)
    Truth.assertThat(result.length).isEqualTo(30)
    Truth.assertThat(result.items.size).isEqualTo(10)
    val item = result.items[0]
    Truth.assertThat(item.id).isEqualTo(0)
    Truth.assertThat(item.name).isEqualTo("Item #0")
    Truth.assertThat(item.description).isEqualTo("This is the Item number 0")
    Truth.assertThat(item.qta).isEqualTo(0)
  }
}