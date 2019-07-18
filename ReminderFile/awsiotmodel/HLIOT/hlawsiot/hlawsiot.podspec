Pod::Spec.new do |s|
    s.name         = "hlawsiot"   #库名称
    s.version      = "0.0.0.1"    #
    s.summary      = "hlawsiot"

    s.description  = <<-DESC
                     TODO: hlawsiot
                     DESC

    s.homepage     = "git@120.92.35.220:wangxianqiang"
    s.license      = { :type => "MIT"}
    s.author       = { "wyze" => "wangxianqiang@ihealthlabs.com.cn" }
    s.source       = { :git => 'git@120.92.35.220:wangxianqiang/zjpart.git', :tag => s.version.to_s }
    
    s.ios.deployment_target = '9.0'
    s.frameworks = "UIKit", "Foundation"
    s.pod_target_xcconfig = {
      'ENABLE_BITCODE' => 'YES',
    }
    
    s.prefix_header_file = 'PrefixHeader.pch'
     
    s.source_files = "module/**/*"
    s.subspec 'netrequest' do |ss|
      ss.source_files = '************/module/netrequest/*.{h,m}'
      end
    
    s.dependency  'AFNetworking', '~> 2.6.3'
    
  end
