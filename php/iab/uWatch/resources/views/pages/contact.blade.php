@extends('layouts.app')

@section('content')
<div class="jumbotron text-center">

    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <div class="container bootstrap snippets">
          <div class="row text-center">
            <div class="col-sm-4">
              <div class="contact-detail-box">
                <i class="fa fa-th fa-3x text-colored"></i>
                <h4>Get In Touch</h4>
                <abbr title="Phone">P:</abbr> (58) 414-357.61.81<br>
                E: <a href="mailto:carmenestelasi@gmail.com" class="text-muted">carmenestelasi@email.com</a>
              </div>
            </div><!-- end col -->
    
            <div class="col-sm-4">
              <div class="contact-detail-box">
                <i class="fa fa-map-marker fa-3x text-colored"></i>
                <h4>Our Location</h4>
    
                <address>
                Carrera 15 entre 43 y 44.<br>
                Barquisimeto, Venezuela<br>
              </address>
              </div>
            </div><!-- end col -->
    
            <div class="col-sm-4">
              <div class="contact-detail-box">
                <i class="fa fa-book fa-3x text-colored"></i>
                <h4>24x7 Support</h4>
    
                <p>Industry's standard dummy text.</p>
                <h4 class="text-muted">1234 567 890</h4>
              </div>
            </div><!-- end col -->
    
          </div>
          <!-- end row -->
    
    
          <div class="row">
            <div class="col-sm-12">
              <div class="contact-map">
                  
                <iframe src="https://www.google.com/maps/embed?pb=!1m10!1m8!1m3!1d15713.841410473819!2d-69.32947199999998!3d10.06131395!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2s!4v1481735102432" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>  </div>
            </div><!-- end col -->
    
            <!-- Contact form -->
            <div class="col-sm-12">
              <form role="form" name="ajax-form" id="ajax-form" action="https://formsubmit.io/send/coderthemes@gmail.com" method="post" class="form-main">
              </form> <!-- /form -->
            </div> <!-- end col -->
          </div> <!-- end row -->
        
        </div>
</div>
@endSection