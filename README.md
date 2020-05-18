# Barocde Parser
This android library is intended to offer capability to extract data from raw barcode string.
Extracted data is available as list of multilanguage labels and optionally formatted data.

## Prerequisities
* Android studio 3.6+
* Android min SDK version >= 21

## Usage
* To add library to project use [Jitpack](https://jitpack.io/)

Then run:

  val barcodeToParse = "0218017596018530"
  val result = BarcodeResultParser().parseBarcode(barcodeToParse), BarcodeFormat.GS1_128) }

## Supported Barcodes
* GS1-128

## Supported Languages
* English
* Polish
