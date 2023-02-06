It is okay if you don’t finish everything outlined below.
When you submit the challenge response,
please note how much time in total you took,
note the places where you think there is space for improvement and be prepared to discuss them.

Coding Challenge Instructions
This test is about creating your own API by collecting data from NASA's APIs for the Mars rovers.

Please read through these instructions carefully before you begin.
What we are looking for

Focus on object-oriented design, for example how the code follows [SOLID](https://en.wikipedia.org/wiki/SOLID) and [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) principles


General code layout
Extensibility of the solution
It is not as important that the implementation actually works as the overall structure and design of the API.
The problem

You are requested to implement an API that can be used to display daily images from the rovers that are on Mars.

For a start, your API should be able to get the navigation camera images from the curiosity rover
from the last 10 days and limit the number of images to 3 per day.

If there are no images for a particular day the result for that day should be empty.

The entry point for your API should be a minimal command-line script.

NASA provides a simple HTTP API that allows for querying of rover images.
This is an example of how to query for the navigation camera images for a particular date:

1
`https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2016-4-2&camera=NAVCAM&api_key=DEMO_KEY
`
NASA's documentation can be found [here](https://api.nasa.gov) (Browse APIs / Mars Rover Photos)
You don't need to specifically add support for the other rovers nor the other kinds of cameras,
but your solution should be easily extended to support multiple rovers, cameras, and other endpoints.

In order to not query the NASA API every time the navigation camera images from the last 10 days are requested,
you should implement a caching layer.

It is not as important where your API caches (be it files, memory, or something else),
the actual cache implementation should be easily replaced with something else.

Example output
Your API should have a simple command-line interface that just outputs JSON.
You don't need to spend time formatting the output.

Example output for the last 10 days of curiosity navigation camera images (with a limit of 3 images per day).

1 2 3 4 5 6 7 8 9 10 11 12 13
`$ cli-script <my cli entrypoint> { "2016-4-6": [], "2016-4-5": [], "2016-4-4": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062102EDR_S0540000NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513062029EDR_S0540000NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01302/opgs/edr/ncam/NLB_513061956EDR_S0540000NCAM00546M_.JPG"], "2016-4-3": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995594EDR_F0540000NCAM07753M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995563EDR_F0540000NCAM07753M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01301/opgs/edr/ncam/NLB_512995472EDR_F0540000NCAM07753M_.JPG"], "2016-4-2": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512914365EDR_F0532980NCAM00320M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512913929EDR_F0532980NCAM00207M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01300/opgs/edr/ncam/NLB_512912740EDR_F0532980NCAM00207M_.JPG"], "2016-4-1": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813334EDR_S0532980NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813261EDR_S0532980NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01299/opgs/edr/ncam/NLB_512813188EDR_S0532980NCAM00546M_.JPG"], "2016-3-31": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NLB_512725460EDR_F0532944NCAM00385M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NLB_512725436EDR_F0532944NCAM00385M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01298/opgs/edr/ncam/NRB_512730078EDR_F0532980NCAM00353M_.JPG"], "2016-3-30": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637743EDR_S0532644NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637670EDR_S0532644NCAM00546M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01297/opgs/edr/ncam/NLB_512637597EDR_S0532644NCAM00546M_.JPG"], "2016-3-29": ["http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555701EDR_F0532644NCAM00354M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555670EDR_F0532644NCAM00354M_.JPG", "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01296/opgs/edr/ncam/NLB_512555645EDR_F0532644NCAM00354M_.JPG"], "2016-3-28": [] }
`

Implementation details
You are free to use any programming language you like.
You should not use any application framework with the exception of potential test frameworks that you'd like to use (such as a unit testing framework).
It is not crucial that the API actually works, the extensibility and design of the API are more important.
Unit tests are not a must but are definitely a plus.
Inspired by

https://github.com/igno/nasa-images

[Connect to preview](https://github.com/igno/nasa-images)
and licensed under the MIT license.
The MIT License (MIT)
Copyright (c) 2016 igno
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.