//
//  HLAwsiotSyncMutableDictionary.h
//  AFNetworking
//
//  Created by jiafangrong on 2019/7/12.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface HLAwsiotSyncMutableDictionary : NSObject

- (id)objectForKey:(id)aKey;
- (void)removeObjectForKey:(id)aKey;
- (void)removeObject:(id)object;
- (void)setObject:(id)anObject forKey:(id <NSCopying>)aKey;
- (NSArray *)allKeys;

@end

NS_ASSUME_NONNULL_END
